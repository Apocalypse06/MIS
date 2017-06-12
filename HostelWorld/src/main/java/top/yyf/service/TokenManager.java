package top.yyf.service;

/**
 * Created by Daniel on 2017/3/2.
 * 负责生成和解析用户token的类
 * 主要使用了{@link top.yyf.util.SecurityUtil}
 */
public interface TokenManager {
    /**
     * 根据用户名获得Token
     *
     * @param username 用户名
     * @return 生成的token
     */
    String getToken(String username);

    /**
     * 根据Token解析用户信息
     *
     * @param token token
     * @return 对应的用户名
     */
    String parseToken(String token);
}
