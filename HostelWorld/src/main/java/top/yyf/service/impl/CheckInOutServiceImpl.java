package top.yyf.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.*;
import top.yyf.entity.*;
import top.yyf.mess.input.CheckIn;
import top.yyf.service.CheckInOutService;
import top.yyf.util.CommonUtil;
import top.yyf.util.PayType;
import top.yyf.util.ReservationState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Daniel on 2017/3/16.
 */
@Service
public class CheckInOutServiceImpl implements CheckInOutService {
    @Autowired
    CheckInDao checkInDao;
    @Autowired
    CheckOutDao checkOutDao;
    @Autowired
    ActualRoomDao actualRoomDao;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    FinancialOrderDao financialOrderDao;
    @Autowired
    ManagerPayOutDao managerPayOutDao;
    @Autowired
    MemberShipDao memberShipDao;

    @Override
    public boolean checkIn(CheckIn checkIn) {
        System.out.println(new Gson().toJson(checkIn));
        CheckInEntity checkInEntity = new CheckInEntity();
        ActualRoomEntity actualRoomEntity;
        ReservationEntity reservationEntity = null;
        //使用预约进行入住
        if (checkIn.getReservationId() != null) {
            reservationEntity = reservationDao.get(checkIn.getReservationId());
            //没到预约时间
            if (reservationEntity == null || !reservationEntity.getFromdate().equals(LocalDate.now()
                    .toString()))
                return false;
            reservationEntity.setState(ReservationState.CHECKED_IN);
            reservationEntity.setIsDelete((byte) 1);
            actualRoomEntity = reservationEntity.getActualRoom();
        } else {
            if (checkIn.getRoomId() == null)
                return false;
            actualRoomEntity = actualRoomDao.get(checkIn.getRoomId());
        }
        //房间非空闲
        if (actualRoomEntity.getIsempty() != 1) {
            return false;
        } else {
            actualRoomEntity.setIsempty((byte) 0);
        }
        if (checkIn.getMemberId() != null) {
            checkInEntity.setMembership(memberShipDao.get(checkIn.getMemberId()));
        }
        checkInEntity.setTime(CommonUtil.getCurrentDateTime());
        checkInEntity.setRoomNum(actualRoomEntity);
        checkInEntity.setPayType(PayType.getPayType(checkIn.getPayType()));
        checkInEntity.setCheckinNum((byte) checkIn.getPersons().size());
        checkInEntity.setCheckInLists(new ArrayList<>());
        checkInEntity.setIsCheckedOut((byte) 0);
        checkIn.getPersons().stream().filter(e -> e.getName().length() != 0 && e.getIdNum().length() != 0)
                .forEach(e -> {
                    CheckinListEntity checkinListEntity = new CheckinListEntity();
                    checkinListEntity.setCheckin(checkInEntity);
                    checkinListEntity.setCustomId(e.getIdNum());
                    checkinListEntity.setName(e.getName());
                    checkInEntity.getCheckInLists().add(checkinListEntity);
                });
        checkInDao.saveOrUpdate(checkInEntity);
        actualRoomDao.saveOrUpdate(actualRoomEntity);
        if (reservationEntity != null)
            reservationDao.saveOrUpdate(reservationEntity);
        return true;
    }

    @Override
    public boolean checkOut(Integer roomId, boolean scorePay) {
        CheckInEntity checkInEntity = checkInDao.getCheckInByRoomId(roomId);

        if (checkInEntity == null)
            return false;
        ActualRoomEntity actualRoomEntity = actualRoomDao.get(roomId);
        CheckOutEntity checkOutEntity = new CheckOutEntity();
        checkOutEntity.setCheckin(checkInEntity);
        checkOutEntity.setTime(CommonUtil.getCurrentDateTime());
        long days = CommonUtil.dateInterval(checkInEntity.getTime().split(" ")[0], LocalDate.now()
                .toString());
        days = (days == 0 ? 1 : days);
        double price = actualRoomEntity.getPrice() * days;

        checkOutEntity.setRealConsume(price);

        FinancialOrderEntity financialOrderEntity = new FinancialOrderEntity();
        financialOrderEntity.setMoney(price);
        financialOrderEntity.setCheckout(checkOutEntity);
        financialOrderEntity.setPayType(checkInEntity.getPayType());
        financialOrderEntity.setTime(LocalDateTime.now().toString());
        financialOrderEntity.setHotel(actualRoomEntity.getHotelId());
        checkOutEntity.setFinancialOrder(financialOrderEntity);

        ManagerPayOutEntity managerPayOutEntity = null;
        MembershipEntity membershipEntity = null;
        //会员支付时创建代付订单和增加会员积分
        if (checkInEntity.getPayType().equals(PayType.MEMBERCARD)) {

            membershipEntity = checkInEntity.getMembership();
            if (scorePay) {
                int score = membershipEntity.getAvailablescore();
                double discount = (double) score / 1000;
                checkOutEntity.setRealConsume(price - discount);
                membershipEntity.setAvailablescore(0);
            }
            membershipEntity.setTotalscore(membershipEntity.getTotalscore() + (int) price);
            membershipEntity.setAvailablescore((membershipEntity.getAvailablescore() + (int) price));
            memberShipDao.saveOrUpdate(membershipEntity);

            managerPayOutEntity = new ManagerPayOutEntity();
            managerPayOutEntity.setFinancialOrder(financialOrderEntity);
            managerPayOutEntity.setMoney(price);
            managerPayOutEntity.setIsPaid((byte) 0);
        }


        checkInEntity.setCheckOut(checkOutEntity);
        checkInEntity.setIsCheckedOut((byte) 1);
        actualRoomEntity.setIsempty((byte) 1);

        try {
            checkOutDao.saveOrUpdate(checkOutEntity);
            checkInDao.saveOrUpdate(checkInEntity);
            actualRoomDao.saveOrUpdate(actualRoomEntity);
            financialOrderDao.saveOrUpdate(financialOrderEntity);
            if (managerPayOutEntity != null)
                managerPayOutDao.saveOrUpdate(managerPayOutEntity);
            if (membershipEntity != null)
                memberShipDao.saveOrUpdate(membershipEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public double getTotalConsume(Integer roomId) {
        CheckInEntity checkInEntity = checkInDao.getCheckInByRoomId(roomId);
        if (checkInEntity == null)
            return -1;
        ActualRoomEntity actualRoomEntity = actualRoomDao.get(roomId);
        long days = CommonUtil.dateInterval(checkInEntity.getTime().split(" ")[0], LocalDate.now()
                .toString());
        days = (days == 0 ? 1 : days);
        return actualRoomEntity.getPrice() * days;
    }

    @Override
    public int getAvailableScore(int roomId) {
        CheckInEntity checkInEntity = checkInDao.getCheckInByRoomId(roomId);
        if (checkInEntity.getMembership() != null)
            return checkInEntity.getMembership().getAvailablescore();
        else
            return -1;
    }
}
