package top.yyf.service;

import top.yyf.entity.UserMonthlyCostEntity;

import java.util.List;

/**
 * Created by Crystal on 2017/6/17.
 * 新系统规划：个人指标部分
 * 1、统计当月活跃会员
 * 2、会员活跃与地域、年龄的关系
 * 3、优惠策略设计
 */
public interface PersonalIndicatorsService {

    /**
     * 根据月份，统计各会员的消费总额
     * @param month
     * @return
     */
    List<UserMonthlyCostEntity> getAllUserMonthlyCost(String month);

}
