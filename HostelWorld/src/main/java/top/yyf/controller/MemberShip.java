package top.yyf.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yyf.mess.input.ReservationInfo;
import top.yyf.mess.retmess.BaseMessage;
import top.yyf.mess.retmess.MemberShipRetMess;
import top.yyf.mess.retmess.ReservationRetMess;
import top.yyf.service.MembershipService;
import top.yyf.service.ReservationService;
import top.yyf.util.ErrorCode;
import top.yyf.util.ParaNames;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Daniel on 2017/3/2.
 * 会员的相关操作
 * 注册成为会员、取消会员、会员信息、暂停会员
 * 支付会费、查看预约信息、预定一个房间、取消预定
 */
@RestController
public class MemberShip {
    @Autowired
    MembershipService membershipService;
    @Autowired
    ReservationService reservationService;

    /**
     * 注册成为会员
     *
     * @param bankNum 银行卡号
     * @return 对应信息
     */
    @RequestMapping(value = "/auth/membership/register", produces = "application/json;charset=UTF-8")
    public String register(String bankNum, HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        String username = (String) request.getAttribute(ParaNames.ID_NAME);
        boolean re = membershipService.register(username, bankNum);
        if (re)
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        return new Gson().toJson(baseMessage);
    }

    /**
     * 取消会员
     *
     * @return 对应信息
     */
    @RequestMapping(value = "/auth/membership/cancel", produces = "application/json;charset=UTF-8")
    public String cancel(HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        String username = (String) request.getAttribute(ParaNames.ID_NAME);
        boolean re = membershipService.cancel(username);
        if (re)
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        return new Gson().toJson(baseMessage);
    }

    /**
     * 会员信息
     *
     * @return {@link MemberShipRetMess}
     */
    @RequestMapping(value = "/auth/membership/info", produces = "application/json;charset=UTF-8")
    public String baseInfo(HttpServletRequest request) {
        BaseMessage<MemberShipRetMess> baseMessage = new BaseMessage<>();
        String username = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            MemberShipRetMess memberShipRtMess = membershipService.beseInfo(username);
            if (memberShipRtMess != null) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
                baseMessage.setRetContent(memberShipRtMess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 暂停会员
     *
     * @return 对应信息
     */
    @RequestMapping(value = "/auth/membership/pause", produces = "application/json;charset=UTF-8")
    public String pause(HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        String username = (String) request.getAttribute(ParaNames.ID_NAME);
        boolean re = membershipService.pause(username);
        if (re)
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        return new Gson().toJson(baseMessage);
    }

    /**
     * 支付会费
     *
     * @return 对应信息
     */
    @RequestMapping(value = "/auth/membership/pay", produces = "application/json;charset=UTF-8")
    public String pay(HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        String username = (String) request.getAttribute(ParaNames.ID_NAME);
        boolean re = membershipService.pay(username);
        if (re)
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得预约信息
     *
     * @return {@link ReservationRetMess}的List
     */
    @RequestMapping(value = "/auth/membership/reservationInfo", produces = "application/json;charset=UTF-8")
    public String reservationInfo(HttpServletRequest request) {
        BaseMessage<List<ReservationRetMess>> baseMessage = new BaseMessage();
        try {
            String username = (String) request.getAttribute(ParaNames.ID_NAME);
            List<ReservationRetMess> reservationRetMesses = reservationService
                    .getReservationInfosByUser(username);
            if (reservationRetMesses != null) {
                baseMessage.setRetContent(reservationRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 预定一个房间
     *
     * @return 对应信息
     */
    @RequestMapping(value = "/auth/membership/book", produces = "application/json;charset=UTF-8")
    public String book(ReservationInfo reservationInfo, HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        try {
            String username = (String) request.getAttribute(ParaNames.ID_NAME);
            boolean f = reservationService.bookRoom(username, reservationInfo);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("预约失败，房间已满");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 取消预定
     *
     * @param reservationId 预约的id
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/membership/cancelReservation", produces = "application/json;charset=UTF-8")
    public String cancelReservation(int reservationId, HttpServletRequest request) {
        BaseMessage baseMessage = new BaseMessage();
        try {
            String username = (String) request.getAttribute(ParaNames.ID_NAME);
            boolean f = reservationService.cancelReservation(username, reservationId);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("预约取消失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }


}
