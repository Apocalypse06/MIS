package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.ActualRoomEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 * 房间Dao
 */
@Repository
public class ActualRoomDao extends BaseDao<ActualRoomEntity, Integer> {
    /**
     * 根据房间计划获得房间
     *
     * @param roomPlanId 房间计划id
     * @return {@link ActualRoomEntity}的List
     */
    public List<ActualRoomEntity> getActualRoomsByRoomPlan(int roomPlanId) {
        return getListByHQL("from ActualRoomEntity where rpId.id=?", roomPlanId);
    }


    /**
     * 根据指定的房间类型、日期获得空闲的房间
     *
     * @param rpId     房间类型id
     * @param fromDate 开始日期
     * @param toDate   结束日期
     * @return {@link ActualRoomEntity}的List
     */
    public List<ActualRoomEntity> getEmptyRoom(int rpId, String fromDate, String toDate) {
        List<ActualRoomEntity> actualRoomEntities = getSession().createSQLQuery("SELECT * FROM " +
                "actual_room WHERE " +
                "rp_id= ? AND " +
                "room_id NOT IN " +
                "(SELECT room_id FROM reservation where rp_id = ? AND is_delete=0 AND todate >= ? AND fromdate <= ?) ").
                addEntity(getEntityClass()).setParameter(0, rpId).setParameter(1, rpId)
                .setParameter(2, fromDate).setParameter(3, toDate).setMaxResults(1).list();
        if (actualRoomEntities == null || actualRoomEntities.size() == 0)
            return null;
        return actualRoomEntities;
    }

    /**
     * 根据开始日期和结束日期获得有剩余房间的酒店信息
     *
     * @param fromDate 开始日期
     * @param toDate   结束日期
     * @return 酒店Id的List
     */
    public List<String> getHotelIdsByEmptyRoom(String fromDate, String toDate) {
        return getSession().createSQLQuery("SELECT DISTINCT hotel_id FROM actual_room " +
                "ar " +
                "WHERE " +
                "room_id NOT IN (SELECT room_id FROM  reservation where rp_id = ar.rp_id AND is_delete=0 AND " +
                "todate >= ? AND fromdate <= ?)").setParameter(0, fromDate)
                .setParameter(1, toDate).list();
    }

    /**
     * 根据酒店id获得对应酒店所有的房间
     *
     * @param hotelId 酒店id
     * @return {@link ActualRoomEntity}的List
     */
    public List<ActualRoomEntity> getRooms(String hotelId) {
        return getListByHQL("from ActualRoomEntity  where hotelId.id=?", hotelId);
    }


}
