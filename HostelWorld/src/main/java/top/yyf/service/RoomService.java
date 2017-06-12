package top.yyf.service;

import top.yyf.mess.input.RoomPlan;
import top.yyf.mess.input.RoomQuery;
import top.yyf.mess.retmess.RoomPlanRetMess;
import top.yyf.mess.retmess.RoomRetMess;

import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2017/3/7.
 * 房间相关逻辑
 * 根据房间查询条件获得房间信息、获得某个酒店的房间类型、添加房间类型
 * 添加具体房间、删除房间计划
 */
public interface RoomService {

    /**
     * 根据房间查询条件获得房间信息
     *
     * @param hotelId   酒店id
     * @param roomQuery 酒店查询 {@link RoomQuery}
     * @return
     */
    Map<String, List<RoomRetMess>> getRoomInfos(String hotelId, RoomQuery roomQuery);

    /**
     * 获得某个酒店的房间类型
     *
     * @param hotelId 酒店id
     * @return {@link RoomPlanRetMess}的List
     */
    List<RoomPlanRetMess> getRoomTypes(String hotelId);

    /**
     * 添加房间类型
     *
     * @param hotelId  酒店id
     * @param roomPlan 房间类型信息 {@link RoomPlan}
     * @return 是否添加成功
     */
    boolean addRoomPlan(String hotelId, RoomPlan roomPlan);

    /**
     * 添加具体房间
     *
     * @param hotelId    酒店id
     * @param roomPlanId 房间计划id
     * @param roomNum    添加的房间的房间号
     * @return 是否添加成功
     */
    boolean addActualRoom(String hotelId, int roomPlanId, String roomNum);

    /**
     * 删除房间计划
     *
     * @param rpId 房间计划id
     * @return 是否删除成功
     */
    boolean deleteRoomPlan(int rpId);

}
