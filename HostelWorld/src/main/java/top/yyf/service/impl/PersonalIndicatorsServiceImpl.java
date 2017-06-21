package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.UserDao;
import top.yyf.dao.UserMonthlyCostDao;
import top.yyf.entity.UserMonthlyCostEntity;
import top.yyf.mess.retmess.UserCosts;
import top.yyf.mess.retmess.UserMonthlyCostRetMess;
import top.yyf.service.PersonalIndicatorsService;

import java.util.List;

/**
 * Created by Crystal on 2017/6/17.
 *
 */
@Service
public class PersonalIndicatorsServiceImpl implements PersonalIndicatorsService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserMonthlyCostDao userMonthlyCostDao;
    /**
     * 根据月份，统计各会员的消费总额
     *
     * @param month
     * @return
     */
    @Override
    public List<UserMonthlyCostRetMess> getAllUserMonthlyCost(String month) {

        List<UserMonthlyCostRetMess> res = userMonthlyCostDao.getAllUserMonthlyCost(month);
        return res;
    }

    @Override
    public UserCosts getUserCosts(String userId) {
        List<UserMonthlyCostEntity> march = userMonthlyCostDao.getUserMonthCosts(userId, "03");
        double marchcost = 0;
        for(UserMonthlyCostEntity userMonthlyCostEntity:march){
            double temp = Double.valueOf(userMonthlyCostEntity.getCost());
            marchcost += temp;
        }

        List<UserMonthlyCostEntity> april = userMonthlyCostDao.getUserMonthCosts(userId, "04");
        double aprilcost = 0;
        for(UserMonthlyCostEntity userMonthlyCostEntity:april){
            double temp = Double.valueOf(userMonthlyCostEntity.getCost());
            aprilcost += temp;
        }

        List<UserMonthlyCostEntity> may = userMonthlyCostDao.getUserMonthCosts(userId, "05");
        double maycost = 0;
        for(UserMonthlyCostEntity userMonthlyCostEntity:may){
            double temp = Double.valueOf(userMonthlyCostEntity.getCost());
            maycost += temp;
        }

        List<UserMonthlyCostEntity> june = userMonthlyCostDao.getUserMonthCosts(userId, "06");
        double junecost = 0;
        for(UserMonthlyCostEntity userMonthlyCostEntity:june){
            double temp = Double.valueOf(userMonthlyCostEntity.getCost());
            junecost += temp;
        }

        UserCosts userCosts = new UserCosts();
        userCosts.userId = userId;
        userCosts.march_cost = marchcost+"";
        userCosts.forth_cost = aprilcost+"";
        userCosts.may_cost = maycost+"";
        userCosts.june_cost = junecost+"";

        return userCosts;
    }
}
