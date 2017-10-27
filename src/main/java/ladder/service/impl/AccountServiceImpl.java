package ladder.service.impl;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import ladder.commons.Constants;
import ladder.commons.Sc2Exception;
import ladder.dao.AccountMapper;
import ladder.dao.MatchPoolMapper;
import ladder.dao.model.Account;
import ladder.dao.model.AccountExample;
import ladder.dao.model.MatchPool;
import ladder.dao.model.MatchPoolExample;
import ladder.service.AccountService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangjian on 17-10-27.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private MatchPoolMapper matchPoolMapper;

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Override
    @Transactional
    public void signUp(String email, String username, String password, String botName, Integer botType, Integer race, String description, MultipartFile bot) throws Exception {
        if (!isEmail(email)) {
            throw new Sc2Exception("Email format error", 11);
        }
        if (username.length() > 50) {
            throw new Sc2Exception("user name format error", 12);
        }
        if (password.length() > 50 || password.length() < 6) {
            throw new Sc2Exception("Password length error", 13);
        }
        if (description.length() > 100) {
            throw new Sc2Exception("Description length error", 14);
        }
        password = encodePassword(password, Constants.SALT);

        String fullBotDirectory = FilenameUtils.concat(Constants.BOT_PATH, botName);
        FileUtils.forceMkdir(new File(fullBotDirectory));
        String fullBotPath = FilenameUtils.concat(fullBotDirectory, botName);
        bot.transferTo(new File(fullBotPath));

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(password);
        account.setBotName(botName);
        account.setBotType(botType);
        account.setRace(race);
        account.setDescription(description);
        account.setBotPath(fullBotPath);
        account.setUpdateTime(new Date());
        try {
            accountMapper.insert(account);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Sc2Exception("Insert account error", 10);
        }
        MatchPool matchPool = new MatchPool();
        matchPool.setBotName(botName);
        matchPool.setBotPath(fullBotPath);
        matchPool.setInMatch(false);
        matchPool.setUsername(username);
        try {
            matchPoolMapper.insert(matchPool);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Sc2Exception("Insert match pool error", 10);
        }
    }

    @Override
    public boolean verifyAccount(String username, String password) throws Exception {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(example);
        if (accounts.size() < 1) {
            return false;
        }
        if (accounts.size() > 1) {
            throw new Sc2Exception("Username duplicate", 14);
        }
        Account account = accounts.get(0);
        password = encodePassword(password, Constants.SALT);
        if (account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBot(String username, String password, MultipartFile bot) throws Exception {
        // verify the username and password
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(example);
        if (accounts.size() < 1) {
            return false;
        }
        if (accounts.size() > 1) {
            throw new Sc2Exception("Username duplicate", 14);
        }
        Account account = accounts.get(0);
        password = encodePassword(password, Constants.SALT);
        if (!account.getPassword().equals(password)) {
            return false;
        }
        // see the bot is in game, if the bot is in game, we should not override the bot file
        MatchPool matchPool = matchPoolMapper.selectByPrimaryKey(username);
        if (matchPool.getInMatch()){
            throw new Sc2Exception("Bot in Game", 15);
        }
        // save new bot
        bot.transferTo(new File(account.getBotPath()));
        // update bot update time in the account database
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);
        return true;

    }

    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
    }

    public static String encodePassword(String password, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return encoderByMd5(encoderByMd5(password) + salt);
    }

}
