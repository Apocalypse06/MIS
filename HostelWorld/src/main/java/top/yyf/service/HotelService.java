package top.yyf.service;

import top.yyf.mess.input.HotelQuery;
import top.yyf.mess.input.HotelRegister;
import top.yyf.mess.retmess.FinancialInfo;
import top.yyf.mess.retmess.HotelRetMess;

import java.util.List;

/**
 * Created by Daniel on 2017/3/7.
 * 提供酒店相关的服务
 * 酒店注册、酒店登录、根据查询条件获得酒店列表、根据酒店id获得酒店的信息
 * 更新酒店信息、根据酒店id获得酒店的财务信息、获得所有没有通过的酒店信息
 */
public interface HotelService {
    /**
     * 酒店注册
     *
     * @param hotelRegister 酒店注册信息  {@link HotelRegister}
     * @return
     */
    String register(HotelRegister hotelRegister);

    /**
     * 酒店登录
     *
     * @param hotelId  酒店id
     * @param password 密码
     * @return 是否登录成功
     */
    boolean login(String hotelId, String password);

    /**
     * 根据查询条件获得酒店列表
     *
     * @return {@link HotelRetMess} 的List
     */
    List<HotelRetMess> hotelInfos(HotelQuery hotelQuery);

    /**
     * 根据酒店id获得酒店的信息
     *
     * @param hotelId 酒店id
     * @return {@link HotelRetMess}酒店信息
     */
    HotelRetMess getHotelInfo(String hotelId);

    /**
     * 更新酒店信息
     *
     * @param hotelId     酒店id
     * @param name        酒店名字
     * @param address     酒店地址
     * @param description 酒店描述
     * @return 是否更新成功
     */
    boolean updateHotelInfo(String hotelId, String name, String address, String description);

    /**
     * 根据酒店id获得酒店的财务信息
     *
     * @param hotelId 酒店id
     * @return {@link FinancialInfo}的List
     */
    List<FinancialInfo> getHotelFinancialInfos(String hotelId);

    /**
     * 获得所有没有通过的酒店信息
     *
     * @return {@link HotelRetMess}的List
     */
    List<HotelRetMess> getUnPassedHotels();

}
