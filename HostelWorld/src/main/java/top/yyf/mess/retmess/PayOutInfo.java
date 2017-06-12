package top.yyf.mess.retmess;

import java.util.List;

/**
 * Created by Daniel on 2017/3/16.
 * 结算信息
 */
public class PayOutInfo {
    /**
     * 酒店id
     */
    public String hotelId;
    /**
     * 酒店名字
     */
    public String hotelName;
    /**
     * 总价
     */
    public double totalPrice;
    /**
     * 结算清单
     */
    public List<PayOutItem> payOutItemList;
}
