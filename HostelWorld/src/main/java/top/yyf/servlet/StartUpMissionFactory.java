package top.yyf.servlet;

import top.yyf.service.AutoService;
import top.yyf.util.ApplicationContextHelper;

/**
 * Created by Daniel on 2017/3/20.
 * 任务创建工厂
 */
public class StartUpMissionFactory {
    /**
     * 获得改变会员状态的任务
     * @return 对应任务
     */
    public static Runnable getMemberStateChangeMission() {
        return () -> {
            AutoService autoService = ApplicationContextHelper.getApplicationContext().getBean
                    (AutoService.class);
            autoService.membershipPause();
            autoService.membershipStop();
        };
    }
}
