package top.yyf.service;

/**
 * Created by Crystal on 2017/6/18.
 * 酒店经营情况相关计算
 * 平均房价ADR = 客房收入/实际客房数量
 * 入住率OCC = 实际出售客房数/总可售房数
 * 平均每间可售房收入REVPAR = 客房收入/可售房数量 = OCC*ADR
 */
public interface HotelBusinessConditionService {

    /**
     * 平均房价ADR = 客房收入/实际客房数量
     * @param hotel
     * @return
     */
    double getADR(String hotel, String month_quanter);

    /**
     * 入住率OCC = 实际出售客房数/总可售房数
     * @param hotel
     * @return
     */
    double getOCC(String hotel, String month_quanter);

    /**
     * 平均每间可售房收入REVPAR = 客房收入/可售房数量 = OCC*ADR
     * @param hotel
     * @return
     */
    double getREVPAR(String hotel, String month_quanter);

}
