package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.RoomPlanEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class RoomPlanDao extends BaseDao<RoomPlanEntity, Integer> {
    public List<RoomPlanEntity> getRoomPlansByHotelId(String hotelId) {
        return getListByHQL("from RoomPlanEntity where hotel.id=?", hotelId);
    }

    public RoomPlanEntity getRoomPlanByName(String roomType, String hotelId) {
        return getByHQL("from RoomPlanEntity where hotel.id=? AND roomType=?", hotelId, roomType);
    }

}
