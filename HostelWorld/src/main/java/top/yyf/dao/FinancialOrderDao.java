package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.FinancialOrderEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 * 财务单
 */
@Repository
public class FinancialOrderDao extends BaseDao<FinancialOrderEntity, Integer> {
    public List<FinancialOrderEntity> getFinancialEntities(String hotelId) {
        return getListByHQL("from FinancialOrderEntity where hotel.id=?", hotelId);
    }

    public List<FinancialOrderEntity> getAllOrders() {
        return getListByHQL("from FinancialOrderEntity");
    }
}
