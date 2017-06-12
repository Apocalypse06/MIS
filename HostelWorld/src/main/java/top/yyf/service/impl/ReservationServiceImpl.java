package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.*;
import top.yyf.entity.*;
import top.yyf.mess.input.ReservationInfo;
import top.yyf.mess.retmess.CheckInRoomRetMess;
import top.yyf.mess.retmess.ReservationRetMess;
import top.yyf.service.ReservationService;
import top.yyf.util.ReservationState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2017/3/11.
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    RoomPlanDao roomPlanDao;
    @Autowired
    ActualRoomDao actualRoomDao;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    UserDao userDao;

    @Override
    public List<ReservationRetMess> getReservationInfosByUser(String username) {
        return reservationDao.getReservationByUser(username).stream().
                map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ReservationRetMess> getReservationInfosByHotel(String hotelId) {
        return reservationDao.getReservationByHotel(hotelId).stream().
                map(this::convert).collect(Collectors.toList());
    }

    @Override
    public boolean bookRoom(String username, ReservationInfo reservationInfo) {
        RoomPlanEntity roomPlan = roomPlanDao.getRoomPlanByName(reservationInfo.getRoomType(),
                reservationInfo.getHotelId());
        List<ActualRoomEntity> actualRoomEntities = actualRoomDao.getEmptyRoom(roomPlan.getRpId(),
                reservationInfo.getFromDate(), reservationInfo.getToDate());
        if (actualRoomEntities == null || actualRoomEntities.size() == 0)
            return false;
        ActualRoomEntity actualRoomEntity = actualRoomEntities.get(0);
        ReservationEntity reservationEntity = new ReservationEntity();
        UserEntity userEntity = userDao.get(username);
        HotelEntity hotelEntity = roomPlan.getHotel();
        reservationEntity.setRoomPlan(roomPlan);
        reservationEntity.setHotel(hotelEntity);
        reservationEntity.setFromdate(reservationInfo.getFromDate());
        reservationEntity.setTodate(reservationInfo.getToDate());
        reservationEntity.setIsDelete((byte) 0);
        reservationEntity.setName(reservationInfo.getName());
        reservationEntity.setPhoneNum(reservationInfo.getPhoneNum());
        reservationEntity.setUser(userEntity);
        reservationEntity.setRevervationTime(LocalDateTime.now().toString());
        reservationEntity.setState(ReservationState.WAIT_TO_CHECK_IN);
        reservationEntity.setActualRoom(actualRoomEntity);
        return handleEntity(reservationEntity);
    }

    @Override
    public boolean cancelReservation(String username, Integer reservationId) {
        ReservationEntity reservationEntity = reservationDao.get(reservationId);
        if (!reservationEntity.getUser().getEmail().equals(username))
            return false;
        reservationEntity.setState(ReservationState.CANCELED);
        reservationEntity.setIsDelete((byte) 1);
        return handleEntity(reservationEntity);
    }

    @Override
    public List<CheckInRoomRetMess> getAccessableRooms(String hotelId) {
        return reservationDao.getReservationByHotel(hotelId).stream().filter(e -> e.getFromdate()
                .equals(LocalDate.now().toString())).
                map(e -> {
                    CheckInRoomRetMess checkInRoomRetMess = new CheckInRoomRetMess();
                    checkInRoomRetMess.roomType = e.getRoomPlan().getRoomType();
                    checkInRoomRetMess.roomNum = e.getActualRoom().getRoomNum();
                    checkInRoomRetMess.roomId = e.getActualRoom().getRoomId();
                    checkInRoomRetMess.memberId = e.getUser().getMerbership().getMerbershipId();
                    checkInRoomRetMess.price = e.getActualRoom().getPrice();
                    return checkInRoomRetMess;
                }).collect(Collectors.toList());
    }

    private boolean handleEntity(ReservationEntity reservationEntity) {
        try {
            reservationDao.saveOrUpdate(reservationEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private ReservationRetMess convert(ReservationEntity reservationEntity) {
        ReservationRetMess reservationRetMess = new ReservationRetMess();
        reservationRetMess.reservationId = reservationEntity.getResId();
        reservationRetMess.hotelName = reservationEntity.getHotel().getName();
        reservationRetMess.fromDate = reservationEntity.getFromdate();
        reservationRetMess.toDate = reservationEntity.getTodate();
        reservationRetMess.name = reservationEntity.getName();
        reservationRetMess.phoneNum = reservationEntity.getPhoneNum();
        reservationRetMess.price = reservationEntity.getRoomPlan().getPrice();
        reservationRetMess.state = reservationEntity.getState();
        reservationRetMess.roomType = reservationEntity.getRoomPlan().getRoomType();
        reservationRetMess.roomId = reservationEntity.getActualRoom().getRoomId();
        reservationRetMess.roomNum = reservationEntity.getActualRoom().getRoomNum();
        return reservationRetMess;
    }
}
