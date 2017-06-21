package top.yyf.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yyf.mess.input.CheckIn;
import top.yyf.mess.input.HotelQuery;
import top.yyf.mess.input.HotelRegister;
import top.yyf.mess.retmess.*;
import top.yyf.service.*;
import top.yyf.util.ErrorCode;
import top.yyf.util.ParaNames;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Daniel on 2017/3/3.
 * 用于提供酒店相关的操作
 * 酒店注册、酒店入住、房间结账、获得对应已入住房间Id的消费总额
 * 酒店信息更新、获得酒店信息、酒店登录、酒店查询、酒店预约信息获取
 * 获得酒店的历史业绩信息、获得未通过的所有酒店
 */
@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    ReservationService reservationService;
    @Autowired
    CheckInOutService checkInOutService;
    @Autowired
    HotelBusinessConditionService hotelBusinessConditionService;

    /**
     * 酒店注册
     *
     * @param hotelRegister 酒店注册需要的信息{@link top.yyf.mess.input.HotelRegister}
     * @return 对应结果
     */
    @RequestMapping(value = "/hotel/hotelRegister", produces = "application/json;charset=UTF-8")
    public String hotelRegister(HotelRegister hotelRegister) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        String id;
        if ((id = hotelService.register(hotelRegister)) != null) {
            baseMessage.setRetCode(ErrorCode.SUCCESS);
            baseMessage.setRetContent(id);
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 酒店入住
     *
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/hotel/checkin", produces = "application/json;charset=UTF-8")
    public String hotelCheckIn(HttpServletRequest request, CheckIn checkIn) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        try {
            boolean f = checkInOutService.checkIn(checkIn);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("入住失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 房间结账
     *
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/hotel/checkout", produces = "application/json;charset=UTF-8")
    public String hotelCheckout(Integer roomId, Boolean scorePay) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        try {
            if (scorePay == null)
                scorePay = false;
            boolean f = checkInOutService.checkOut(roomId, scorePay);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("入住失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得对应已入住房间Id的消费总额
     *
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/hotel/totalConsume", produces = "application/json;charset=UTF-8")
    public String getTotalConsume(Integer roomId) {
        BaseMessage<ConsumeAndScore> baseMessage = new BaseMessage<>();
        try {
            ConsumeAndScore consumeAndScore = new ConsumeAndScore();
            consumeAndScore.totalConsume = checkInOutService.getTotalConsume(roomId);
            consumeAndScore.score = checkInOutService.getAvailableScore(roomId);
            baseMessage.setRetContent(consumeAndScore);
            baseMessage.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 酒店信息更新
     *
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/hotel/update", produces = "application/json;charset=UTF-8")
    public String hotelUpdate(String name, String address, String description, HttpServletRequest request) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        try {
            String hotelId = Util.getId(request);
            boolean f = hotelService.updateHotelInfo(hotelId, name, address, description);
            if (f) {
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            } else {
                baseMessage.setRetMess("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetMess("服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    /**
     * 获得酒店信息
     *
     * @param request http请求
     * @return {@link HotelRetMess}
     */
    @RequestMapping(value = "/auth/hotel/info", produces = "application/json;charset=UTF-8")
    public String info(String hotelId, HttpServletRequest request) {
        BaseMessage<HotelRetMess> hotelRetMessBaseMessage = new BaseMessage<>();
        try {
            String id = (String) request.getAttribute(ParaNames.ID_NAME);
            id = (hotelId != null ? hotelId : id);
            HotelRetMess hotelRetMess = hotelService.getHotelInfo(id);
            if (hotelRetMess != null) {
                hotelRetMessBaseMessage.setRetCode(ErrorCode.SUCCESS);
                hotelRetMessBaseMessage.setRetContent(hotelRetMess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(hotelRetMessBaseMessage);
    }

    /**
     * 酒店登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 对应结果
     */
    @RequestMapping(value = "/hotel/login", produces = "application/json;charset=UTF-8")
    public String hotelLogin(String username, String password) {
        BaseMessage<String> baseMessage = new BaseMessage<>();
        try {
            if (hotelService.login(username, password)) {
                baseMessage.setRetContent(tokenManager.getToken(username));
                baseMessage.setRetCode(ErrorCode.SUCCESS);
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
     * 酒店查询
     *
     * @param hotelQuery 查询参数{@link top.yyf.mess.input.HotelQuery}
     * @return {@link HotelRetMess}的list
     */
    @RequestMapping(value = "/hotel/query", produces = "application/json;charset=UTF-8")
    public String hotelQuery(HotelQuery hotelQuery) {
        BaseMessage<List<HotelRetMess>> baseMessage = new BaseMessage<>();
        try {
            List<HotelRetMess> hotelRetMesses = hotelService.hotelInfos(hotelQuery);
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
     * 酒店预约信息获取
     *
     * @return 对应结果
     */
    @RequestMapping(value = "/auth/hotel/reservation", produces = "application/json;charset=UTF-8")
    public String hotelReservation(HttpServletRequest request) {
        BaseMessage<List<ReservationRetMess>> baseMessage = new BaseMessage<>();
        String hotelId = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            List<ReservationRetMess> hotelRetMesses = reservationService
                    .getReservationInfosByHotel(hotelId);
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
     * 获得酒店的历史业绩信息
     *
     * @param request http请求
     * @return {@link FinancialInfo}的list
     */
    @RequestMapping(value = "/auth/hotel/financial_his", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelFinancialInfo(HttpServletRequest request) {
        BaseMessage<List<FinancialInfo>> baseMessage = new BaseMessage<>();
        String hotelId = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            List<FinancialInfo> hotelRetMesses = hotelService
                    .getHotelFinancialInfos(hotelId);
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
     * 获得未通过的所有酒店
     * 总经理调用
     *
     * @param request http请求，用于获取token
     * @return {@link HotelRetMess}的list
     */
    @RequestMapping(value = "/auth/hotel/unpassed", produces = "application/json;" +
            "charset=UTF-8")
    public String unpassedHotels(HttpServletRequest request) {
        BaseMessage<List<HotelRetMess>> baseMessage = new BaseMessage<>();
        try {
            List<HotelRetMess> hotelRetMesses = hotelService
                    .getUnPassedHotels();
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


    @RequestMapping(value = "/auth/hotel/business_his", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelBusinessInfo(HttpServletRequest request) {
        BaseMessage<List<BusinessInfo>> baseMessage = new BaseMessage<>();
        String hotelId = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            List<BusinessInfo> hotelRetMesses = hotelBusinessConditionService.getBusinessInfos(hotelId);

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

    @RequestMapping(value = "/auth/hotel/placenumber_his", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelPlaceNumberInfo(HttpServletRequest request) {
        BaseMessage<List<PlaceNumber>> baseMessage = new BaseMessage<>();
        String hotelId = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            List<PlaceNumber> placeNumberList = hotelBusinessConditionService.getPlaceNumber(hotelId);

            if (placeNumberList != null) {
                baseMessage.setRetContent(placeNumberList);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

    @RequestMapping(value = "/auth/hotel/agenumber_his", produces = "application/json;" +
            "charset=UTF-8")
    public String hotelAgeNumberInfo(HttpServletRequest request) {
        BaseMessage<List<AgeNumber>> baseMessage = new BaseMessage<>();
        String hotelId = (String) request.getAttribute(ParaNames.ID_NAME);
        try {
            List<AgeNumber> ageNumberList = hotelBusinessConditionService.getAgeNumber(hotelId);

            if (ageNumberList != null) {
                baseMessage.setRetContent(ageNumberList);
                baseMessage.setRetCode(ErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessage.setRetCodeAndMess(ErrorCode.SERVERFAIL, "服务器错误");
        }
        return new Gson().toJson(baseMessage);
    }

}
