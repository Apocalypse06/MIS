package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.UserEntity;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class UserDao extends BaseDao<UserEntity, String> {

    /**
     * 根据月份获得各会员的消费总额
     * @param month
     * @return
     */
//    public List<UserMonthlyCostRetMess> getAllUserMonthlyCost(String month){
//
//    }
}
