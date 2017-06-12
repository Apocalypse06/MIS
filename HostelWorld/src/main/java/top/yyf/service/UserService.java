package top.yyf.service;

import top.yyf.mess.input.UserRegister;
import top.yyf.mess.retmess.UserRetMess;

/**
 * Created by Daniel on 2017/3/7.
 * 用户相关操作
 * 用户注册、用户登录、判断用户是否存在、根据用户名获得用户信息
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param userRegister 用户注册信息 {@link UserRegister}
     * @return 是否注册成功
     */
    boolean register(UserRegister userRegister);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    boolean login(String username, String password);

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return 用户是否存在
     */
    boolean isExisted(String username);

    /**
     * 根据用户名获得用户信息
     *
     * @param username 用户名
     * @return {@link UserRetMess}
     */
    UserRetMess getUserInfo(String username);

}
