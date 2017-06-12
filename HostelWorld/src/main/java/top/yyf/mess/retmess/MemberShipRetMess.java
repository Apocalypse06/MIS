package top.yyf.mess.retmess;

import top.yyf.util.MemberShipState;

/**
 * Created by Daniel on 2017/3/7.
 * 会员信息
 */
public class MemberShipRetMess {
    /**
     * 会员卡号
     */
    public String carId;
    /**
     * 开通日期
     */
    public String openDate;
    /**
     * 上次支付会费日期
     */
    public String lastPayDate;
    /**
     * 总积分
     */
    public int score;
    /**
     * 可用积分
     */
    public int availableScore;
    /**
     * 排名
     */
    public int rank;
    /**
     * 等级
     */
    public int level;
    /**
     * 会员状态
     * 0：正常
     * 1：欠费
     */
    public MemberShipState state;
}
