package ladder.service.impl;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import ladder.commons.Constants;
import ladder.commons.Sc2Exception;
import ladder.dao.AccountMapper;
import ladder.dao.InsertAccountMapper;
import ladder.dao.MatchPoolMapper;
import ladder.dao.StatisticalListMapper;
import ladder.dao.model.*;
import ladder.service.AccountService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
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
    @Resource
    private StatisticalListMapper statisticalListMapper;
    @Resource
    private InsertAccountMapper insertAccountMapper;

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Override
    @Transactional
    public void signUp(String email, String username, String password, String botName, Integer botType, Integer race, String description, MultipartFile bot, MultipartFile config) throws Exception {
        // do verify work
        if(!isDll(bot)){
            throw new Sc2Exception("Bot file Format Error: bot Name:" + bot.getOriginalFilename(), 15);
        }
        if (!isEmail(email)) {
            throw new Sc2Exception("Email Format Error.", 11);
        }
        if (username.length() > 50) {
            throw new Sc2Exception("User Name Format Error.", 12);
        }
        if (password.length() > 50 || password.length() < 6) {
            throw new Sc2Exception("Password Length Error.", 13);
        }
        if (description.length() > 100) {
            throw new Sc2Exception("Description Length Error.", 14);
        }
        password = encodePassword(password, Constants.SALT);
        // save bot to the file
        String fullBotDirectory = FilenameUtils.concat(Constants.getBotPath(), botName);
        FileUtils.forceMkdir(new File(fullBotDirectory));
        String fullBotPath = FilenameUtils.concat(fullBotDirectory, botName+".dll");
        String fullConfigPath = FilenameUtils.concat(fullBotDirectory, "config.json");

        bot.transferTo(new File(fullBotPath));
        config.transferTo(new File(fullConfigPath));
        // set up account
        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(password);
        account.setBotName(botName);
        account.setBotType(botType);
        account.setRace(race);
        account.setDescription(description);
        account.setBotPath(fullBotPath);
        account.setConfigPath(fullConfigPath);
        account.setUpdateTime(new Date());

        try {
            insertAccountMapper.insertAccount(account.getUsername(),
                    account.getEmail(),
                    account.getPassword(),
                    account.getBotName(),
                    account.getBotType(),
                    account.getRace(),
                    account.getDescription(),
                    account.getBotPath(),
                    account.getConfigPath(),
                    account.getUpdateTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Sc2Exception("Insert Account Error", 10);
        }
        // put it into match pool
        MatchPool matchPool = new MatchPool();
        matchPool.setBotName(botName);
        matchPool.setBotPath(fullBotPath);
        matchPool.setConfigPath(fullConfigPath);
        matchPool.setInMatch(false);
        matchPool.setUsername(username);
        try {
            matchPoolMapper.insert(matchPool);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Sc2Exception("Insert Match Pool Error.", 15);
        }
        // put it into season ladder
        StatisticalList statisticalList = new StatisticalList();
        statisticalList.setSeason(Constants.SEASON);
        statisticalList.setBotName(botName);
        statisticalList.setMatches(0);
        statisticalList.setUsername(username);
        statisticalList.setWinRate(0F);
        statisticalList.setWins(0);
        try {
            statisticalListMapper.insert(statisticalList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Sc2Exception("Insert Ladder Statistical List Error.", 16);
        }
    }

    @Override
    public boolean verifyAccount(String username, String password) throws Exception {
        Account account = accountMapper.selectByPrimaryKey(username);
        if (account == null) {
            return false;
        }
        password = encodePassword(password, Constants.SALT);
        if (account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBot(String username, String password, MultipartFile bot, MultipartFile config) throws Exception {
        // verify the username and password
        Account account = accountMapper.selectByPrimaryKey(username);
        if (account == null) {
            return false;
        }
        password = encodePassword(password, Constants.SALT);
        if (!account.getPassword().equals(password)) {
            return false;
        }
        // see the bot is in game, if the bot is in game, we should not override the bot file
        MatchPool matchPool = matchPoolMapper.selectByPrimaryKey(username);
        if (matchPool.getInMatch()){
            throw new Sc2Exception("Bot is Playing Game, Please Wait.", 15);
        }
        // save new bot
        bot.transferTo(new File(account.getBotPath()));
        config.transferTo(new File(account.getConfigPath()));
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
    private static boolean isDll(MultipartFile bot){
        String botName = bot.getOriginalFilename();
        return botName.endsWith(".dll") || botName.endsWith(".DLL");
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
