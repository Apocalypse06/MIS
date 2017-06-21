package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.UserMonthlyCostEntity;
import top.yyf.mess.retmess.UserMonthlyCostRetMess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Crystal on 2017/6/17.
 */
@Repository
public class UserMonthlyCostDao extends BaseDao<UserMonthlyCostEntity, String> {

    public List<UserMonthlyCostRetMess> getAllUserMonthlyCost(String month) {

        List<String> memberShip = getMemberShip(month);
        List<Double> totalConsume = getSumConsume(month);
        List<String> time = getTime(month);
        int size = memberShip.size();

        List<UserMonthlyCostRetMess> res = new ArrayList<>();

        for(int i = 0;i<size;i++){
            UserMonthlyCostRetMess userMonthlyCostRetMess = new UserMonthlyCostRetMess();
            userMonthlyCostRetMess.setUserId(memberShip.get(i));
            userMonthlyCostRetMess.setCost(totalConsume.get(i)+"");
            userMonthlyCostRetMess.setDate(time.get(i));

            res.add(userMonthlyCostRetMess);
        }
//        for(UserMonthlyCostRetMess userMonthlyCostRetMess:res){
//            UserMonthlyCostEntity userMonthlyCostEntity = new UserMonthlyCostEntity();
//            userMonthlyCostEntity.setDate(userMonthlyCostRetMess.getDate());
//            userMonthlyCostEntity.setUserId(userMonthlyCostRetMess.getUserId());
//            userMonthlyCostEntity.setCost(userMonthlyCostRetMess.getCost());
//
//            saveOrUpdate(userMonthlyCostEntity);
//        }


        return res;
    }

    public List<String> getMemberShip(String month){
        String sqlString = "select tempTable.membership_id from" +
                "  (SELECT check_in.time as time, is_checked_out, membership_id, check_in.checkin_id as in_id, checkout_id as out_id, real_consume " +
                " from check_in, check_out where check_in.checkin_id = check_out.checkin_id) as tempTable " +
                " where tempTable.is_checked_out = 1 and tempTable.time like ? " +
                " GROUP BY tempTable.membership_id, tempTable.time";

        return getSession().createSQLQuery(sqlString).setParameter(0, "%-"+month+"-%").list();
    }

    public List<Double> getSumConsume(String month){
        String sqlString = "select sum(real_consume) from" +
                "  (SELECT check_in.time as time, is_checked_out, membership_id, check_in.checkin_id as in_id, checkout_id as out_id, real_consume " +
                " from check_in, check_out where check_in.checkin_id = check_out.checkin_id) as tempTable " +
                " where tempTable.is_checked_out = 1 and tempTable.time like ? " +
                " GROUP BY tempTable.membership_id, tempTable.time";

        return getSession().createSQLQuery(sqlString).setParameter(0, "%-"+month+"-%").list();
    }

    public List<String> getTime(String month){
        String sqlString = "select tempTable.time from" +
                "  (SELECT check_in.time as time, is_checked_out, membership_id, check_in.checkin_id as in_id, checkout_id as out_id, real_consume " +
                " from check_in, check_out where check_in.checkin_id = check_out.checkin_id) as tempTable " +
                " where tempTable.is_checked_out = 1 and tempTable.time like ? " +
                " GROUP BY tempTable.membership_id, tempTable.time";

        return getSession().createSQLQuery(sqlString).setParameter(0, "%-"+month+"-%").list();
    }

    public List<UserMonthlyCostEntity> getUserMonthCosts(String userId, String month){
        String sqlString = "SELECT * FROM user_monthly_cost WHERE user_id =? and date LIKE ? ";

        return getSession().createSQLQuery(sqlString).setParameter(0, userId).setParameter(1, "%-"+month+"-%").list();
    }


}
