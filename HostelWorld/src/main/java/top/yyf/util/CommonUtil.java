package top.yyf.util;


import com.google.gson.JsonParser;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * 通用工具类
 *
 * @author Aaron
 * @date 2014-6-23
 */
public class CommonUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern
            ("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMMATTER = DateTimeFormatter.ofPattern
            ("yyyy-MM-dd");

    /**
     * 获得长度为length的随机字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        if (length <= 0)
            return "";
        String base = "abcdefghijklmnopqrstuvwxzy0123456789";
        int size = base.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * size);
            result.append(base.charAt(index));
        }
        return result.toString();
    }

    public static boolean isJson(String json) {
        return new JsonParser().parse(json).isJsonObject();
    }

    public static boolean fuzzyMatch(String a, String b) {
        return FuzzySearch.ratio(a, b) > 85;
    }

    public static long dateInterval(String startDate, String endDate) {
        return Period.between(getDate(startDate), getDate(endDate)).getDays();
    }

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime getDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    public static LocalDate getDate(String date) {
        return LocalDate.parse(date, DATE_FORMMATTER);

    }

    public static void main(String[] args) {
        System.out.println(dateInterval("2016-01-02", "2016-01-03"));
    }
}