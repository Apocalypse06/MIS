package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.ReservationEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class ReservationDao extends BaseDao<ReservationEntity, Integer> {
    public List<ReservationEntity> getReservationByHotel(String hotelId) {
        return getListByHQL("from ReservationEntity where hotel.id=? AND state ='待入住' ", hotelId);
    }

    public List<ReservationEntity> getReservationByUser(String email) {
        return getListByHQL("from ReservationEntity where user.id=?", email);
    }


}
