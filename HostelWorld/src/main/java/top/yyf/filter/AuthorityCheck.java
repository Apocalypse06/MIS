package top.yyf.filter;

import com.google.gson.Gson;
import top.yyf.mess.retmess.BaseMessage;
import top.yyf.service.TokenManager;
import top.yyf.util.ApplicationContextHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static top.yyf.util.ParaNames.ID_NAME;

/**
 * Created by Daniel on 2017/3/2.
 * 对用户权限进行验证
 * 在/api/auth/**路由路径下的资源访问需要进行权限验证
 * 用户需要将自己的token当做参数传入，这里根据获得的token进行解码，获取对应的用户名
 * 信息编码解码参照{@link top.yyf.util.SecurityUtil}
 */
@WebFilter(filterName = "AuthorityCheck", urlPatterns = "/api/auth/*")
public class AuthorityCheck implements Filter {
    TokenManager tokenManager;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (token == null) {
            BaseMessage baseMessage = new BaseMessage(406, "Token not found!");
            resp.getWriter().println(new Gson().toJson(baseMessage));
        } else {
            token = token.replace(' ', '+');
            String username = tokenManager.parseToken(token);
            if (username == null) {
                BaseMessage<String> baseMessage = new BaseMessage<>(406, "Wrong Token!");
                resp.getWriter().println(new Gson().toJson(baseMessage));
            } else {
                req.setAttribute(ID_NAME, username);
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        tokenManager = ApplicationContextHelper.getApplicationContext().getBean(TokenManager.class);
    }

}
