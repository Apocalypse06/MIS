package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.*;
import top.yyf.entity.*;
import top.yyf.mess.retmess.*;
import top.yyf.service.ManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2017/3/16.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    HotelDao hotelDao;
    @Autowired
    ManagerPayOutDao managerPayOutDao;
    @Autowired
    ManagerDao managerDao;
    @Autowired
    ActualRoomDao actualRoomDao;
    @Autowired
    FinancialOrderDao financialOrderDao;
    @Autowired
    MemberShipDao memberShipDao;
    @Autowired
    ReservationDao reservationDao;

    @Override
    public boolean passHotel(String hotelId) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        hotelEntity.setIsPassed((byte) 1);
        try {
            hotelDao.saveOrUpdate(hotelEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean payOut(List<Integer> payOrders) {
        payOrders.stream().forEach(this::payOutSingleOrder);
        return true;
    }

    @Override
    public boolean payOut(String hotelId) {
        managerPayOutDao.getUnpaiedInfosByhotel(hotelId).forEach(e -> {
            System.out.println(e.getMoney());
            e.setIsPaid((byte) 1);
            managerPayOutDao.saveOrUpdate(e);
        });
        return true;
    }

    private void payOutSingleOrder(int integer) {
        ManagerPayOutEntity managerPayOutEntity = managerPayOutDao.get(integer);
        managerPayOutEntity.setIsPaid((byte) 1);
        managerPayOutDao.saveOrUpdate(managerPayOutEntity);
    }

    @Override
    public List<PayOutInfo> getUnPaiedInfos() {
        List<PayOutInfo> re = new ArrayList<>();
        managerPayOutDao.getUnpaiedInfos().stream().collect(Collectors.groupingBy(e -> e
                .getFinancialOrder().getHotel())).forEach((key, value) -> {
            PayOutInfo payOutInfo = new PayOutInfo();
            payOutInfo.hotelId = key.getHotelId();
            payOutInfo.hotelName = key.getName();
            payOutInfo.totalPrice = 0;
            payOutInfo.payOutItemList = value.stream().map(e -> {
                PayOutItem payOutItem = new PayOutItem();
                payOutItem.dateTime = e.getFinancialOrder().getTime();
                payOutItem.totalPrice = e.getMoney();
                payOutItem.orderNum = e.getMpoId();
                payOutInfo.totalPrice += e.getMoney();
                return payOutItem;
            }).collect(Collectors.toList());
            re.add(payOutInfo);
        });
        return re;
    }

    @Override
    public boolean login(String username, String password) {
        ManagerEntity managerEntity = managerDao.get(username);
        return managerEntity != null && managerEntity.getPassword().equals(password);
    }

    @Override
    public List<HotelRoomRetMess> getHotelRoomInfos() {
        return hotelDao.getAllHotels().stream().map(e -> {
            HotelRoomRetMess hotelRoomRetMess = new HotelRoomRetMess();
            hotelRoomRetMess.hotelId = e.getHotelId();
            hotelRoomRetMess.hotelName = e.getName();
            List<ActualRoomEntity> actualRoomEntities = actualRoomDao.getRooms(e.getHotelId());
            hotelRoomRetMess.totalRoomNum = actualRoomEntities.size();
            hotelRoomRetMess.usedRoomNum = (int) actualRoomEntities.stream().
                    filter(k -> k.getIsempty() == 0).count();
            return hotelRoomRetMess;
        }).collect(Collectors.toList());
    }

    @Override
    public List<FinancialInfo> getTotalFinancialInfo() {
        return Util.getFinancialInfo(financialOrderDao.getAllOrders());
    }

    @Override
    public MemStaRetMess getMemberShipState() {
        List<MembershipEntity> membershipEntities = memberShipDao.getAllMemberShip();
        MemStaRetMess memStaRetMess = new MemStaRetMess();
        memStaRetMess.totalMemberNum = membershipEntities.size();
        membershipEntities.forEach(e -> {
            memStaRetMess.totalConsume += e.getTotalscore();
            memStaRetMess.maxConsume = Math.max(memStaRetMess.maxConsume, e.getTotalscore());
        });
        memStaRetMess.aveConsume = memStaRetMess.totalConsume / (memStaRetMess.totalMemberNum
                + 0.00000000000000001);
        List<ReservationEntity> reservations = reservationDao.getListByHQL("from " +
                "ReservationEntity");
        memStaRetMess.totalReservationNum = reservations.size();
        reservations.forEach(e -> {
            if (e.getState().equals("成功入住"))
                memStaRetMess.successReservationNum += 1;
        });
        return memStaRetMess;
    }
}
