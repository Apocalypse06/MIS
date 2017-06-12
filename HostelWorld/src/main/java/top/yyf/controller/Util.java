package top.yyf.controller;

import top.yyf.util.ParaNames;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Daniel on 2017/3/15.
 * 控制器的相关工具类
 */
class Util {
    /**
     * 获得HttpRequest中保存的TOKEN值
     *
     * @param request http请求
     * @return token值
     */
    static String getId(HttpServletRequest request) {
        return (String) request.getAttribute(ParaNames.ID_NAME);
    }
}
