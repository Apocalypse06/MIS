package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.UserDao;
import top.yyf.dao.UserMonthlyCostDao;
import top.yyf.entity.UserMonthlyCostEntity;
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
    public List<UserMonthlyCostEntity> getAllUserMonthlyCost(String month) {

        List<UserMonthlyCostEntity> res = userMonthlyCostDao.getAllUserMonthlyCost(month);
        return res;
    }
}
