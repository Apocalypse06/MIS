package top.yyf.service;

import top.yyf.mess.retmess.FinancialInfo;
import top.yyf.mess.retmess.HotelRoomRetMess;
import top.yyf.mess.retmess.MemStaRetMess;
import top.yyf.mess.retmess.PayOutInfo;

import java.util.List;

/**
 * Created by Daniel on 2017/3/11.
 * 经理相关逻辑
 * 经理登录、通过对应id酒店的申请、支付对应订单信息、结算对应酒店的待结算的金额
 * 获得未结算的费用、获得所有酒店及房间信息、获得总体的经营状态信息、获得会员状态的统计信息
 */
public interface ManagerService {

    /**
     * 经理登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    boolean login(String username, String password);

    /**
     * 通过对应id酒店的申请
     *
     * @param hotelId 酒店id
     * @return 是否操作成功
     */
    boolean passHotel(String hotelId);

    /**
     * 支付对应订单信息
     *
     * @param payOrders 需要支付的订单的id的List
     * @return 是否支付成功
     */
    boolean payOut(List<Integer> payOrders);

    /**
     * 结算对应酒店的待结算的金额
     *
     * @param hotelId 酒店id
     * @return 是否结算成功
     */
    boolean payOut(String hotelId);

    /**
     * 获得未结算的费用
     *
     * @return {@link PayOutInfo}的List
     */
    List<PayOutInfo> getUnPaiedInfos();

    /**
     * 获得所有酒店及房间信息
     *
     * @return {@link HotelRoomRetMess}的List
     */
    List<HotelRoomRetMess> getHotelRoomInfos();

    /**
     * 获得总体的经营状态信息
     *
     * @return {@link FinancialInfo}的List
     */
    List<FinancialInfo> getTotalFinancialInfo();

    /**
     * 获得会员状态的统计信息
     *
     * @return {@link MemStaRetMess}
     */
    MemStaRetMess getMemberShipState();

}
