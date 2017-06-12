package top.yyf.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import top.yyf.util.AppParameters;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.scheduletask.TaskBuilder;
import top.yyf.util.scheduletask.TaskEntity;
import top.yyf.util.scheduletask.TaskManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.TimeUnit;

import static top.yyf.util.ParaNames.TOKEN;
import static top.yyf.util.ParaNames.TOKEN_TIME;


/**
 * Created by Daniel on 2017/2/28.
 * 自启动的任务类
 */
public class StartUp extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //加载Spring上下文Helper
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ApplicationContextHelper.setApplicationContext(ctx);

        try {
            TaskEntity taskEntity = TaskBuilder.createBuilder().setName("状态检验").addTask
                    (StartUpMissionFactory
                            .getMemberStateChangeMission()).asRepeatableTask().frequency(1, TimeUnit.DAYS)
                    .firstRunTime("00:00:00").build();
            TaskManager.registerTask(taskEntity);
            TaskManager.scheduleMonitor();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //读取参数
        AppParameters.put(TOKEN, config.getInitParameter(TOKEN));
        AppParameters.put(TOKEN_TIME, config.getInitParameter(TOKEN_TIME));
//        AppParameters.print();
        System.out.println("server started");
    }
}
