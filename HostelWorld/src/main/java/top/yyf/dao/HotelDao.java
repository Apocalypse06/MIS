package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.HotelEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class HotelDao extends BaseDao<HotelEntity, String> {
    public String getMaxId() {
        List li = getSession().createQuery("from HotelEntity order by id " +
                "desc")
                .setMaxResults
                        (1).list();
        if (li == null || li.size() == 0)
            return null;
        return ((HotelEntity) li.get(0)).getHotelId();
    }

    List<HotelEntity> getHotelsByName(String hotelName) {
        return getListByHQL("from HotelEntity where name like ? AND is_passed=1", hotelName);
    }

    public List<HotelEntity> getAllHotels() {
        return getListByHQL("from HotelEntity where is_passed = 1");
    }

    public List<HotelEntity> getUnpassedHotels() {
        return getListByHQL("from HotelEntity where is_passed=0");
    }

}
