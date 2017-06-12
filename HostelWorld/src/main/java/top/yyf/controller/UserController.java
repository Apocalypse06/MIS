package top.yyf.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yyf.mess.input.UserRegister;
import top.yyf.mess.retmess.BaseMessage;
import top.yyf.mess.retmess.UserRetMess;
import top.yyf.service.TokenManager;
import top.yyf.service.UserService;
import top.yyf.util.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static top.yyf.util.ParaNames.ID_NAME;

/**
 * Created by Daniel on 2017/3/2.
 * 用户操作
 */
@RestController()
public class UserController {
    @Autowired
    TokenManager tokenManager;
    @Autowired
    UserService userService;

    /**
     * 普通用户注册
     *
     * @param userRegister 用户注册信息 {@link UserRegister}
     * @return 对应结果
     */
    @RequestMapping(value = "/user/register", produces = "application/json;charset=UTF-8")
    public String userRegister(UserRegister userRegister) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        if (userService.isExisted(userRegister.getEmail())) {
            baseMessage.setRetCode(ErrorCode.EXISTED);
            baseMessage.setRetMess("用户已存在");
        } else {
            userService.register(userRegister);
            baseMessage.setRetContent(tokenManager.getToken(userRegister.getEmail()));
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        }
        return new Gson().toJson(baseMessage);
    }


    /**
     * 用户信息获取
     *
     * @return {@link UserRetMess}的List
     */
    @RequestMapping(value = "/auth/user/info", produces = "application/json;charset=UTF-8")
    public String userInfo(HttpServletRequest request, HttpServletResponse response) {
        BaseMessage<UserRetMess> baseMessage = new BaseMessage<>();
        String username = (String) request.getAttribute(ID_NAME);
        UserRetMess userRetMess = userService.getUserInfo(username);
        if (userRetMess != null) {
            baseMessage.setRetCode(ErrorCode.SUCCESS);
            baseMessage.setRetContent(userRetMess);
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 对应结果
     */
    @RequestMapping(value = "/user/login", produces = "application/json;charset=UTF-8")
    public String logonIn(@RequestParam String username, @RequestParam String password) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        if (userService.login(username, password)) {
            baseMessage.setRetCode(ErrorCode.SUCCESS);
            baseMessage.setRetMess("success");
            baseMessage.setRetContent(tokenManager.getToken(username));
        } else {
            baseMessage.setRetMess("登录失败");
        }
        System.out.println(new Gson().toJson(baseMessage));
        return new Gson().toJson(baseMessage);
    }
}
