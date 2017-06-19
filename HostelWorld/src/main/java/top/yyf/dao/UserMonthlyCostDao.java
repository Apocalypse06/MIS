package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.UserMonthlyCostEntity;

import java.util.List;

/**
 *
 * Created by Crystal on 2017/6/17.
 */
@Repository
public class UserMonthlyCostDao extends BaseDao<UserMonthlyCostEntity, String> {

    public List<UserMonthlyCostEntity> getAllUserMonthlyCost(String month) {

        String sqlString = "select tempTable.membership_id, sum(real_consume), tempTable.time from" +
                "  (SELECT check_in.time as time, is_checked_out, membership_id, check_in.checkin_id as in_id, checkout_id as out_id, real_consume " +
                " from check_in, check_out where check_in.checkin_id = check_out.checkin_id) as tempTable " +
                " where tempTable.is_checked_out = 1 and tempTable.time like ? " +
                " GROUP BY tempTable.membership_id, tempTable.time";

        return getListBySQL(sqlString, "%-"+month+ "-%");
    }
}
