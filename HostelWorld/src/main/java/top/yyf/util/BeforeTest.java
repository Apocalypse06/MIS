package top.yyf.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import static top.yyf.util.ParaNames.TOKEN;
import static top.yyf.util.ParaNames.TOKEN_TIME;

/**
 * Created by Daniel on 2017/3/11.
 */
public class BeforeTest {

    private String userToken = "uEvdfUMvEBE3iTfTyYtvxL2MxRVYywEeyV9kR5pwMoxlfpMkqoa0Etu1N9BGMK3y";

    public static void beforeTest() {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext
                ("applicationContext.xml");
        cxt.start();
        ApplicationContextHelper.setApplicationContext(cxt);

        AppParameters.put(TOKEN, "token");
        AppParameters.put(TOKEN_TIME, "300000");
    }
}
