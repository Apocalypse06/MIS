package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.ActualRoomDao;
import top.yyf.dao.HotelDao;
import top.yyf.dao.RoomPlanDao;
import top.yyf.entity.ActualRoomEntity;
import top.yyf.entity.HotelEntity;
import top.yyf.entity.RoomPlanEntity;
import top.yyf.mess.input.RoomPlan;
import top.yyf.mess.input.RoomQuery;
import top.yyf.mess.retmess.RoomPlanRetMess;
import top.yyf.mess.retmess.RoomRetMess;
import top.yyf.service.RoomService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2017/3/11.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomPlanDao roomPlanDao;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    ActualRoomDao actualRoomDao;

    @Override
    public Map<String, List<RoomRetMess>> getRoomInfos(String hotelId, RoomQuery roomQuery) {
        String type = roomQuery.getRoomType();
        Integer isEmpty = roomQuery.getRoomState();
        List<ActualRoomEntity> actualRoomEntities = actualRoomDao.getRooms(hotelId);
        if (type != null) {
            actualRoomEntities = actualRoomEntities.stream().filter(e -> e.getRpId().getRoomType()
                    .equals(type)).collect(Collectors.toList());
        }
        if (isEmpty != null) {
            actualRoomEntities = actualRoomEntities.stream().filter(e -> ((int) e.getIsempty())
                    == isEmpty).collect(Collectors.toList());
        }
        return actualRoomEntities.stream().map(this::convert).collect(Collectors.groupingBy
                (e -> e.roomType));
    }

    @Override
    public List<RoomPlanRetMess> getRoomTypes(String hotelId) {
        return roomPlanDao.getRoomPlansByHotelId(hotelId).stream().map(e -> {
            RoomPlanRetMess roomPlanRetMess = new RoomPlanRetMess();
            roomPlanRetMess.accommodations = e.getAccommodation();
            roomPlanRetMess.description = e.getDescription();
            List<ActualRoomEntity> actualRoomEntities = actualRoomDao.getActualRoomsByRoomPlan(e
                    .getRpId());
            roomPlanRetMess.totalRoomNums = actualRoomEntities.size();
            roomPlanRetMess.leftRoomNums = (int) actualRoomEntities.stream().
                    filter(k -> k.getIsempty() != 0).count();
            roomPlanRetMess.price = e.getPrice();
            roomPlanRetMess.roomType = e.getRoomType();
            roomPlanRetMess.startDate = e.getFromdate();
            roomPlanRetMess.endDate = e.getTodate();
            roomPlanRetMess.rpId = e.getRpId();
            return roomPlanRetMess;
        }).collect(Collectors.toList());
    }


    @Override
    public boolean addRoomPlan(String hotelId, RoomPlan roomPlan) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        RoomPlanEntity roomPlanEntity = new RoomPlanEntity();
        roomPlanEntity.setRoomType(roomPlan.getType());
        roomPlanEntity.setFromdate(roomPlan.getStartDate());
        roomPlanEntity.setTodate(roomPlan.getEndDate());
        roomPlanEntity.setDescription(roomPlan.getDes());
        roomPlanEntity.setPrice(roomPlan.getPrice());
        roomPlanEntity.setHotel(hotelEntity);
        roomPlanEntity.setAccommodation((byte) roomPlan.getAccommodations());
        try {
            roomPlanDao.saveOrUpdate(roomPlanEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean addActualRoom(String hotelId, int roomPlanId, String roomNum) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        RoomPlanEntity roomPlan = roomPlanDao.get(roomPlanId);
        ActualRoomEntity actualRoomEntity = new ActualRoomEntity();
        actualRoomEntity.setRoomNum(roomNum);
        actualRoomEntity.setHotelId(hotelEntity);
        actualRoomEntity.setRpId(roomPlan);
        actualRoomEntity.setIsempty((byte) 1);
        actualRoomEntity.setPrice(roomPlan.getPrice());
        try {
            actualRoomDao.saveOrUpdate(actualRoomEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRoomPlan(int rpId) {
        roomPlanDao.deleteById(rpId);
        return true;
    }

    private RoomRetMess convert(ActualRoomEntity actualRoomEntity) {
        RoomRetMess roomRetMess = new RoomRetMess();
        roomRetMess.price = actualRoomEntity.getPrice();
        roomRetMess.roomNum = actualRoomEntity.getRoomNum();
        roomRetMess.roomState = actualRoomEntity.getIsempty();
        roomRetMess.roomType = actualRoomEntity.getRpId().getRoomType();
        roomRetMess.roomId = actualRoomEntity.getRoomId();
        return roomRetMess;
    }

}
