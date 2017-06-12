package top.yyf.util;

/**
 * Created by Daniel on 2017/3/16.
 * 支付类型
 */
public class PayType {
    /**
     * 现金
     */
    public static final String CASH = "CASH";
    /**
     * 会员卡
     */
    public static final String MEMBERCARD = "MEMBERCARD";

    public static final String getPayType(String ch) {
        switch (ch) {
            case "现金":
                return CASH;
            case "会员卡":
                return MEMBERCARD;
        }
        return null;
    }
}
