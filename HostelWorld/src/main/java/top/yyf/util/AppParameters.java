package top.yyf.util;

import java.util.concurrent.ConcurrentHashMap;

import static top.yyf.util.ParaNames.TOKEN;
import static top.yyf.util.ParaNames.TOKEN_TIME;

/**
 * Created by Daniel on 2017/3/11.
 * 当前保存的参数信息，在 {@link top.yyf.servlet.StartUp}中进行了初始化
 */
public class AppParameters {


    public static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    public static void put(String key, String value) {
        concurrentHashMap.put(key, value);
    }

    public static String get(String key) {
        return concurrentHashMap.get(key);
    }

    public static void print() {
        System.out.println("Params");
        System.out.println(TOKEN + "=" + get(TOKEN));
        System.out.println(TOKEN_TIME + "=" + get(TOKEN_TIME));
    }

}
