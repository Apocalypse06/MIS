package top.yyf.service;

import top.yyf.mess.input.CheckIn;

/**
 * Created by Daniel on 2017/3/16.
 * 入离店的一系列操作
 * 房间入住、房间结账、获得总的消费金额、获得对应房间入住人会员卡上可用的积分
 */
public interface CheckInOutService {
    /**
     * 房间入住
     *
     * @param checkIn 入住信息 {@link CheckIn}
     * @return 是否成功
     */
    boolean checkIn(CheckIn checkIn);

    /**
     * 房间结账
     *
     * @param roomId   房间id
     * @param scorePay 使用的积分
     * @return 是否结账成功
     */
    boolean checkOut(Integer roomId, boolean scorePay);

    /**
     * 获得总的消费金额
     *
     * @param roomId 房间id
     * @return 消费金额
     */
    double getTotalConsume(Integer roomId);

    /**
     * 获得对应房间入住人会员卡上可用的积分，如果不是会员则返回-1
     *
     * @param roomId 房间id
     * @return 会员积分
     */
    int getAvailableScore(int roomId);


}
