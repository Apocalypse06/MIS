package top.yyf.service.impl;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.ActualRoomDao;
import top.yyf.dao.FinancialOrderDao;
import top.yyf.dao.HotelDao;
import top.yyf.dao.RoomPlanDao;
import top.yyf.entity.HotelEntity;
import top.yyf.entity.RoomPlanEntity;
import top.yyf.mess.input.HotelQuery;
import top.yyf.mess.input.HotelRegister;
import top.yyf.mess.retmess.FinancialInfo;
import top.yyf.mess.retmess.HotelRetMess;
import top.yyf.service.HotelService;
import top.yyf.service.IdGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2017/3/11.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    IdGenerator idGenerator;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    RoomPlanDao roomPlanDao;
    @Autowired
    ActualRoomDao actualRoomDao;
    @Autowired
    FinancialOrderDao financialOrderDao;

    @Override
    public String register(HotelRegister hotelRegister) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setAddress(hotelRegister.getAddress());
        hotelEntity.setDescription(hotelRegister.getDescription());
        hotelEntity.setIsPassed((byte) 0);
        hotelEntity.setName(hotelRegister.getName());
        hotelEntity.setPassword(hotelRegister.getPassword());
        hotelEntity.setHotelId(idGenerator.generateNextHotelId());
        try {
            hotelDao.saveOrUpdate(hotelEntity);
            return hotelEntity.getHotelId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean login(String hotelId, String password) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        return hotelEntity != null && hotelEntity.getIsPassed() != 0 &&
                hotelEntity.getPassword().equals(password);
    }

    /**
     * 获得所有未审批的酒店
     *
     * @return
     */
    @Override
    public List<HotelRetMess> getUnPassedHotels() {
        return hotelDao.getUnpassedHotels().stream().map(e -> getHotelInfo(e.getHotelId())).collect
                (Collectors.toList());
    }


    @Override
    public List<HotelRetMess> hotelInfos(HotelQuery hotelQuery) {
        String startDate = hotelQuery.getStartDate() == null ? LocalDate.now().toString() : hotelQuery
                .getStartDate();
        String endDate = hotelQuery.getEndDate() == null ? LocalDate.now().toString() :
                hotelQuery.getEndDate();
        return actualRoomDao.getHotelIdsByEmptyRoom(startDate, endDate).stream().map
                (this::getHotelInfo).sorted((e1, e2) -> {
            int score1 = FuzzySearch.ratio(e1.hotelName, hotelQuery.getHotelName());
            int score2 = FuzzySearch.ratio(e2.hotelName, hotelQuery.getHotelName());
            return score1 > score2 ? -1 : (score1 < score2 ? 1 : 0);
        }).collect(Collectors.toList());
    }

    @Override
    public HotelRetMess getHotelInfo(String hotelId) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        HotelRetMess hotelRetMess = new HotelRetMess();
        hotelRetMess.hotelAddress = hotelEntity.getAddress();
        hotelRetMess.hotelName = hotelEntity.getName();
        hotelRetMess.hotelDescription = hotelEntity.getDescription();
        hotelRetMess.hotelId = hotelEntity.getHotelId();
        List<RoomPlanEntity> roomPlanEntities = roomPlanDao.getRoomPlansByHotelId(hotelId);
        if (roomPlanEntities != null && roomPlanEntities.size() != 0) {
            hotelRetMess.roomTypes = roomPlanEntities.stream().map
                    (RoomPlanEntity::getRoomType).collect(Collectors.toList());
            hotelRetMess.startPrice = roomPlanEntities.stream().map(RoomPlanEntity::getPrice).min(
                    (e1, e2) -> (e1 < e2 ? -1 : (e1 > e2) ? 1 : 0)).get();
        }
        return hotelRetMess;
    }

    @Override
    public boolean updateHotelInfo(String hotelId, String name, String address, String description) {
        HotelEntity hotelEntity = hotelDao.get(hotelId);
        hotelEntity.setName(name);
        hotelEntity.setAddress(address);
        hotelEntity.setDescription(description);
        try {
            hotelDao.saveOrUpdate(hotelEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FinancialInfo> getHotelFinancialInfos(String hotelId) {
        return Util.getFinancialInfo(financialOrderDao.getFinancialEntities(hotelId));
    }
}
