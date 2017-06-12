package top.yyf.service;

import top.yyf.mess.input.ReservationInfo;
import top.yyf.mess.retmess.CheckInRoomRetMess;
import top.yyf.mess.retmess.ReservationRetMess;

import java.util.List;

/**
 * Created by Daniel on 2017/3/7.
 * 预约信息操作
 */
public interface ReservationService {
    /**
     * 根据用户名获得预约列表
     *
     * @param username 用户名
     * @return {@link ReservationRetMess}的List
     */
    List<ReservationRetMess> getReservationInfosByUser(String username);

    /**
     * 根据酒店id获得预约列表
     *
     * @param hotelId 酒店id
     * @return {@link ReservationRetMess}的List
     */
    List<ReservationRetMess> getReservationInfosByHotel(String hotelId);

    /**
     * 预定房间
     *
     * @param username 用户名
     * @return 是否预定成功
     */
    boolean bookRoom(String username, ReservationInfo reservationInfo);

    /**
     * 取消预约
     *
     * @param username      用户名
     * @param reservationId 预约id
     * @return 是否取消成功
     */
    boolean cancelReservation(String username, Integer reservationId);

    /**
     * 获得当前可以入住的房间信息
     *
     * @param hotelId 酒店id
     * @return {@link CheckInRoomRetMess}的List
     */
    List<CheckInRoomRetMess> getAccessableRooms(String hotelId);

}
