package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.HotelDao;
import top.yyf.dao.MemberShipDao;
import top.yyf.service.IdGenerator;

/**
 * Created by Daniel on 2017/3/11.
 */
@Service
public class IdGeneratorImpl implements IdGenerator {
    @Autowired
    MemberShipDao memberShipDao;
    @Autowired
    HotelDao hotelDao;

    @Override
    public String generateNextMembershipId() {
        String maxId = memberShipDao.getMaxId();
        if (maxId == null)
            return "m000001";
        else
            return "m" + String.format("%06d", Integer.valueOf(maxId.substring(1)) + 1);
    }

    @Override
    public String generateNextHotelId() {
        String maxId = hotelDao.getMaxId();
        if (maxId == null)
            return "h000001";
        else
            return "h" + String.format("%06d", Integer.valueOf(maxId.substring(1)) + 1);
    }
}
