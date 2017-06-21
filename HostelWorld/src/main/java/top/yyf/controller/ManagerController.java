package top.yyf.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yyf.mess.retmess.*;
import top.yyf.service.HotelBusinessConditionService;
import top.yyf.service.ManagerService;
import top.yyf.service.TokenManager;
import top.yyf.util.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Daniel on 2017/3/18.
 * 提供经理相关的操作
 * 通过酒店的申请、获得总经理待结算的清单、根据酒店的id对酒店进行结算、经理登录
 * 获得所有酒店的房间信息、获得总体的经营情况、获得会员状态的统计信息
 */
@RestController
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    HotelBusinessConditionService hotelBusinessConditionService;

    /**
     * 通过酒店的申请
     *
     * @param hotelId 酒店id
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/manager/pass", produces = "application/json;" +
            "charset=UTF-8")
    public String passHotel(String hotelId) {
        BaseMessage baseMessage = new BaseMessage<>();
        try {
            boolean f = managerService.passHotel(hotelId);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("通过失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得总经理待结算的清单
     *
     * @param request http请求
     * @return {@link PayOutInfo} 的List
     */
    @RequestMapping(value = "/auth/manager/unpaiedInfo", produces = "application/json;" +
            "charset=UTF-8")
    public String getUnpaiedInfos(HttpServletRequest request) {
        BaseMessage<List<PayOutInfo>> baseMessage = new BaseMessage<>();
        try {
            List<PayOutInfo> hotelRetMesses = managerService
                    .getUnPaiedInfos();
            if (hotelRetMesses != null) {
                baseMessage.setRetContent(hotelRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 根据酒店的id对酒店进行结算
     *
     * @param hotelId 酒店id
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/manager/pay", produces = "application/json;" +
            "charset=UTF-8")
    public String pay(String hotelId) {
        BaseMessage baseMessage = new BaseMessage<>();
        try {
            boolean f = managerService.payOut(hotelId);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("通过失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 经理登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 对应结果
     */
    @RequestMapping(value = "/manager/login", produces = "application/json;" +
            "charset=UTF-8")
    public String loginIn(String username, String password) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        try {
            boolean f = managerService.login(username, password);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
                baseMessage.setRetContent(tokenManager.getToken(username));
            } else {
                baseMessage.setRetMess("账号或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得所有酒店的房间信息
     *
     * @param request http请求
     * @return {@link HotelRoomRetMess}的List
     */
    @RequestMapping(value = "/auth/manager/hotelroominfo", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelRoomInfo(HttpServletRequest request) {
        BaseMessage<List<HotelRoomRetMess>> baseMessage = new BaseMessage<>();
        try {
            List<HotelRoomRetMess> hotelRetMesses = managerService
                    .getHotelRoomInfos();
            if (hotelRetMesses != null) {
                baseMessage.setRetContent(hotelRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得总体的经营情况
     *
     * @param request httpRequest
     * @return {@link FinancialInfo} 的List
     */
    @RequestMapping(value = "/auth/manager/financial", produces = "application/json;" +
            "charset=UTF-8")
    public String financialInfo(HttpServletRequest request) {
        BaseMessage<List<FinancialInfo>> baseMessage = new BaseMessage<>();
        try {
            List<FinancialInfo> financialInfo = managerService
                    .getTotalFinancialInfo();
            if (financialInfo != null) {
                baseMessage.setRetContent(financialInfo);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }


    /**
     * 获得会员状态的统计信息
     *
     * @param request httpRequest
     * @return {@link MemStaRetMess}
     */
    @RequestMapping(value = "/auth/manager/memberstate", produces = "application/json;" +
            "charset=UTF-8")
    public String memberStatInfo(HttpServletRequest request) {
        BaseMessage<MemStaRetMess> baseMessage = new BaseMessage<>();
        try {
            MemStaRetMess memberShipState = managerService
                    .getMemberShipState();
            if (memberShipState != null) {
                baseMessage.setRetContent(memberShipState);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    @RequestMapping(value = "/auth/manager/hotelturnoverinfo", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelTurnoverInfo(HttpServletRequest request) {
        BaseMessage<List<HotelTurnoverMess>> baseMessage = new BaseMessage<>();
        try {
            List<HotelTurnoverMess> hotelRetMesses = hotelBusinessConditionService.getHotelTurnoverMess();
            if (hotelRetMesses != null) {
                baseMessage.setRetContent(hotelRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    @RequestMapping(value = "/auth/manager/hotelturnoverinfoById", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelTurnoverInfoById(HttpServletRequest request) {
        BaseMessage<List<HotelTurnoverIncrease>> baseMessage = new BaseMessage<>();
        try {
            List<HotelTurnoverIncrease> hotelRetMesses = hotelBusinessConditionService.getHotelTurnoverMessById("h000001");
            if (hotelRetMesses != null) {
                baseMessage.setRetContent(hotelRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    @RequestMapping(value = "/auth/manager/hotelturnoverinfoById2", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelTurnoverInfoById2(HttpServletRequest request) {
        BaseMessage<List<HotelTurnoverIncrease>> baseMessage = new BaseMessage<>();
        try {
            List<HotelTurnoverIncrease> hotelRetMesses = hotelBusinessConditionService.getHotelTurnoverMessById("h000002");
            if (hotelRetMesses != null) {
                baseMessage.setRetContent(hotelRetMesses);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }



}
