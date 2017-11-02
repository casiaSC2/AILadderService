package ladder.controllers;


import ladder.commons.BaseResponse;
import ladder.commons.ErrorJson;
import ladder.commons.Sc2Exception;
import ladder.commons.SuccessJson;
import ladder.service.AccountService;
import ladder.service.LadderListService;
import ladder.service.MatchResultService;
import ladder.vos.LadderList;
import ladder.vos.MatchList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by wangjian on 17-10-24.
 */
@RestController
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    @Resource
    private AccountService accountService;
    @Resource
    private LadderListService ladderListService;

    @Resource
    private MatchResultService matchResultService;

    @RequestMapping(value="/download_replay",method=RequestMethod.GET)
    public void testDownload(HttpServletResponse resp,
                             @RequestParam(name = "path") String path) throws IOException {
        File file = new File(path);
        String fileName = "replay.sc2replay";
        resp.setHeader("content-type", "application/octet-stream");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = resp.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }

    @RequestMapping(value = "/view_result", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse viewResult(@RequestParam(name = "username") String username){
        try {
            MatchList matchList = matchResultService.getMatches(username);
            return matchList;
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(1, "Unknown error");
        }
    }

    @RequestMapping(value = "/ladder", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse ladder(@RequestParam(name = "season") Integer season){
        try{
            LadderList ladderList = ladderListService.getLadderList(season);
            return ladderList;
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(1, "Unknown error");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "bot") MultipartFile bot) {
        try {
            boolean result = accountService.updateBot(username, password, bot);
            if (result) {
                return new SuccessJson();
            } else {
                return new ErrorJson(21, "Invalid username or password");
            }
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(1, "Unknown error");
        }
    }

    @RequestMapping("/sign_up")
    @ResponseBody
    public BaseResponse signUp(@RequestParam(name = "email") String email,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "botName") String botName,
                               @RequestParam(name = "botType") Integer botType,
                               @RequestParam(name = "race") Integer race,
                               @RequestParam(name = "description", required = false) String description,
                               @RequestParam(name = "bot") MultipartFile bot) {
        try {
            accountService.signUp(email, username, password, botName, botType, race, description, bot);
            return new SuccessJson();
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(1, "Unknown error");
        }

    }


}
