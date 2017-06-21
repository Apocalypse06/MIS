package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.ActualRoomDao;
import top.yyf.dao.FinancialOrderDao;
import top.yyf.dao.HotelDao;
import top.yyf.dao.RoomPlanDao;
import top.yyf.entity.ActualRoomEntity;
import top.yyf.entity.HotelEntity;
import top.yyf.mess.retmess.*;
import top.yyf.service.HotelBusinessConditionService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crystal on 2017/6/18.
 * 酒店经营情况相关计算
 * 平均房价ADR = 客房收入/实际客房数量
 * 入住率OCC = 实际出售客房数/总可售房数
 * 平均每间可售房收入REVPAR = 客房收入/可售房数量 = OCC*ADR
 */
@Service
public class HotelBusinessConditionServiceImpl implements HotelBusinessConditionService {

    @Autowired
    FinancialOrderDao financialOrderDao;
    @Autowired
    ActualRoomDao actualRoomDao;
    @Autowired
    RoomPlanDao roomPlanDao;
    @Autowired
    HotelDao hotelDao;

    /**
     * 平均房价ADR = 客房收入/实际客房数量
     *
     * @param hotel
     * @return
     */
    @Override
    public double getADR(String hotel, String month_quanter) {
        DecimalFormat df = new DecimalFormat("0.00");
        List<FinancialInfo> financialInfoList = Util.getFinancialInfo(financialOrderDao.getFinancialEntities(hotel, month_quanter));
        double totalIncome = 0;
        for(FinancialInfo financialInfo:financialInfoList){
//            System.out.println(financialInfo.date+" "+
//                    financialInfo.cash_income+" "+financialInfo.member_income+" "+financialInfo.total_income);
            totalIncome += financialInfo.total_income;
        }
        System.out.println("当月客房收入："+totalIncome);

        List<ActualRoomEntity> actualRoomEntityList = actualRoomDao.getUsedRooms(hotel, month_quanter);

        System.out.println("实际售出房间数量："+actualRoomEntityList.size());

        if(actualRoomEntityList.size() != 0){
            String str = df.format(totalIncome / actualRoomEntityList.size());
            System.out.println("平均房价ADR："+str);
            return Double.valueOf(str);
        }else{
            return 0;
        }
    }

    /**
     * 入住率OCC = 实际出售客房数/总可售房数
     *
     * @param hotel
     * @return
     */
    @Override
    public double getOCC(String hotel, String month_quanter) {
        int num = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        List<ActualRoomEntity> actualRoomEntityList = actualRoomDao.getUsedRooms(hotel, month_quanter);

        System.out.println("实际售出房间数量："+actualRoomEntityList.size());

        List<ActualRoomEntity> actualRoomEntityList1 = actualRoomDao.getRoomNumByHotelAndDate(hotel, month_quanter);

        num = actualRoomEntityList1.size();
        System.out.println("总房间数量："+num);

        if(num != 0){
            String str = df.format(actualRoomEntityList.size()*1.0 / num*1.0);
            System.out.println("入住率OCC："+str);
            return Double.valueOf(str);
        }else{
            return 0;
        }
    }

    /**
     * 平均每间可售房收入REVPAR = 客房收入/可售房数量 = OCC*ADR
     *
     * @param hotel
     * @return
     */
    @Override
    public double getREVPAR(String hotel, String month_quanter) {
        DecimalFormat df = new DecimalFormat("0.00");

        double ADR = getADR(hotel, month_quanter);
        double OCC = getOCC(hotel, month_quanter);
        double REVPAR = ADR * OCC;

        System.out.println("平均每间可售房收入REVPAR："+Double.valueOf(df.format(REVPAR)));
        return Double.valueOf(df.format(REVPAR));

    }

    @Override
    public List<BusinessInfo> getBusinessInfos(String hotelId) {

        List<BusinessInfo> businessInfos = new ArrayList<>();

        for(int i = 3;i<7;i++){
            BusinessInfo businessInfo = new BusinessInfo();
            businessInfo.ADR = getADR(hotelId, "0"+i);
            businessInfo.OCC = getOCC(hotelId, "0"+i);
            businessInfo.REVPAR = getREVPAR(hotelId, "0"+i);
            businessInfo.date = i+"月";

            businessInfos.add(businessInfo);
        }

        System.out.println(businessInfos.size());
        return businessInfos;
    }

    @Override
    public List<PlaceNumber> getPlaceNumber(String hotel) {

        List<PlaceNumber> placeNumbers = hotelDao.getPlaceNumberByHotelId(hotel);
        for(PlaceNumber placeNumber:placeNumbers){
            System.out.println(placeNumber.place+" "+ placeNumber.number);
        }
        return placeNumbers;
    }

    @Override
    public List<AgeNumber> getAgeNumber(String hotel) {

        List<AgeNumber> ageNumbers = hotelDao.getAgeNumberByHotelId(hotel);
        for(AgeNumber ageNumber:ageNumbers){
            System.out.println(ageNumber.age+" "+ageNumber.number);
        }

        return ageNumbers;
    }

    /**
     * （经理）获得不同hotel的营业信息
     *
     * @return
     */
    @Override
    public List<HotelTurnoverMess> getHotelTurnoverMess() {
        List<HotelTurnoverMess> hotelTurnoverMessList = new ArrayList<>();

        List<HotelEntity> hotelEntities = hotelDao.getAllHotels();

        for(int i = 0;i<hotelEntities.size();i++){
            for(int j = 3;j<7;j++){
                HotelEntity hotel = hotelEntities.get(i);
                List<FinancialInfo> financialInfoList = Util
                        .getFinancialInfo(financialOrderDao.getFinancialEntities(hotel.getHotelId(), "0"+j));

                double totalIncome = 0;
                for(FinancialInfo financialInfo:financialInfoList){
//            System.out.println(financialInfo.date+" "+
//                    financialInfo.cash_income+" "+financialInfo.member_income+" "+financialInfo.total_income);
                    totalIncome += financialInfo.total_income;
                }
                System.out.println("酒店："+hotel.getHotelId()+" "+j+"月客房收入："+totalIncome);

                HotelTurnoverMess hotelTurnoverMess = new HotelTurnoverMess();
                hotelTurnoverMess.hotel = hotel.getHotelId();
                hotelTurnoverMess.currentMonth = "2017年"+j+"月";
                hotelTurnoverMess.currentTurnover = totalIncome + "元";
                hotelTurnoverMess.city = hotel.getAddress_city();
                hotelTurnoverMess.joinDate = hotel.getSet_up_date();

                hotelTurnoverMessList.add(hotelTurnoverMess);
            }
        }
        return hotelTurnoverMessList;
    }

    /**
     * 根据id获得客栈营业额和增长率
     *
     * @param hotelId
     * @return
     */
    @Override
    public List<HotelTurnoverIncrease> getHotelTurnoverMessById(String hotelId) {

        List<HotelTurnoverIncrease> hotelTurnoverIncreaseList = new ArrayList<>();

        double[] increase = new double[5];

        for(int j = 3;j<7;j++) {
            HotelEntity hotel = hotelDao.getHotelById(hotelId);

            List<FinancialInfo> financialInfoList = Util
                    .getFinancialInfo(financialOrderDao.getFinancialEntities(hotel.getHotelId(), "0" + j));

            double totalIncome = 0;
            for (FinancialInfo financialInfo : financialInfoList) {
//            System.out.println(financialInfo.date+" "+
//                    financialInfo.cash_income+" "+financialInfo.member_income+" "+financialInfo.total_income);
                totalIncome += financialInfo.total_income;
            }
            System.out.println("酒店：" + hotel.getHotelId() + " " + j + "月"+" 客房收入：" + totalIncome);

            increase[j-3] = totalIncome;

        }
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i = 0;i<4;i++){
            HotelTurnoverIncrease hotelTurnoverIncrease = new HotelTurnoverIncrease();
            hotelTurnoverIncrease.hotelId = hotelId;
            hotelTurnoverIncrease.month = (i+3)+"月";
            hotelTurnoverIncrease.turnover = increase[i]+"";
            if(i == 0){
                hotelTurnoverIncrease.increase = 1+"";
            }else{
                hotelTurnoverIncrease.increase = df.format( (increase[i] - increase[i-1]) / increase[i-1] );
            }
            hotelTurnoverIncreaseList.add(hotelTurnoverIncrease);

        }

        return hotelTurnoverIncreaseList;
    }


}
