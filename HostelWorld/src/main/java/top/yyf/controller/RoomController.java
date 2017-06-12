package top.yyf.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yyf.mess.input.RoomPlan;
import top.yyf.mess.input.RoomQuery;
import top.yyf.mess.retmess.BaseMessage;
import top.yyf.mess.retmess.RoomPlanRetMess;
import top.yyf.mess.retmess.RoomRetMess;
import top.yyf.service.RoomService;
import top.yyf.util.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2017/3/15.
 * 提供房间相关的操作
 */
@RestController
public class RoomController {
    @Autowired
    RoomService roomService;

    /**
     * 酒店剩余房间查询
     *
     * @param roomQuery 放假查询
     * @param request   http 请求
     * @return String ->{@link RoomRetMess}的list 的map
     */
    @RequestMapping(value = "/auth/ac_room/info", produces = "application/json;charset=UTF-8")
    public String hotelRoomInfo(RoomQuery roomQuery, HttpServletRequest request) {
        BaseMessage<Map<String, List<RoomRetMess>>> baseMessage = new BaseMessage<>();
        try {
            String hotelId = Util.getId(request);
            Map<String, List<RoomRetMess>> roomPlanRetMesses =
                    roomService.getRoomInfos(hotelId, roomQuery);
            baseMessage.setRetContent(roomPlanRetMesses);
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }


    /**
     * 在对应的房间计划下增加一个房间
     *
     * @param request http请求
     * @param rpId    房间计划的id
     * @param roomNum 房间号
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/ac_room/add", produces = "application/json;charset=UTF-8")
    public String addRoom(HttpServletRequest request, int rpId, String roomNum) {
        BaseMessage baseMessage = new BaseMessage<>();
        try {
            String hotelId = Util.getId(request);
            boolean f = roomService.addActualRoom(hotelId, rpId, roomNum);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 删除房间计划
     *
     * @param rpId 房间计划的id
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/room_pan/delete", produces = "application/json;charset=UTF-8")
    public String deleteRoomPlan(int rpId) {
        BaseMessage baseMessage = new BaseMessage<>();
        try {
            boolean f = roomService.deleteRoomPlan(rpId);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }


    /**
     * 增加房间计划
     *
     * @param request  http请求
     * @param roomPlan 房间计划信息 {@link RoomPlan}
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/room_plan/add", produces = "application/json;charset=UTF-8")
    public String addRoomPlan(HttpServletRequest request, RoomPlan roomPlan) {
        BaseMessage baseMessage = new BaseMessage<>();
        try {
            String hotelId = Util.getId(request);
            boolean f = roomService.addRoomPlan(hotelId, roomPlan);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得酒店的房间计划信息
     *
     * @param request http请求
     * @return {@link RoomPlanRetMess}的list
     */
    @RequestMapping(value = "/auth/room_plan/info", produces = "application/json;charset=UTF-8")
    public String hotelRoomPlan(HttpServletRequest request) {
        BaseMessage<List<RoomPlanRetMess>> baseMessage = new BaseMessage<>();
        try {
            String hotelId = Util.getId(request);
            List<RoomPlanRetMess> roomPlanRetMesses = roomService.getRoomTypes(hotelId);
            baseMessage.setRetContent(roomPlanRetMesses);
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }


}
