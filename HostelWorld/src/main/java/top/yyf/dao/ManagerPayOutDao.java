package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.ManagerPayOutEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class ManagerPayOutDao extends BaseDao<ManagerPayOutEntity, Integer> {
    public List<ManagerPayOutEntity> getUnpaiedInfos() {
        return getListByHQL("from ManagerPayOutEntity where isPaid = 0");
    }

    public List<ManagerPayOutEntity> getUnpaiedInfosByhotel(String hotelId) {
        return getListByHQL("from ManagerPayOutEntity where isPaid = 0 AND financialOrder.hotel" +
                ".id=?", hotelId);
    }
}
