package top.yyf.service.impl;

import top.yyf.entity.FinancialOrderEntity;
import top.yyf.mess.retmess.FinancialInfo;
import top.yyf.util.PayType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2017/3/20.
 */
public class Util {
    protected static List<FinancialInfo> getFinancialInfo(List<FinancialOrderEntity> financialOrderEntitynList) {
        List<FinancialInfo> result = new ArrayList<>();
        financialOrderEntitynList.stream().map(e -> {
            FinancialInfo hotelFinancialInfo = new FinancialInfo();
            hotelFinancialInfo.date = e.getTime().split("T")[0];
            if (e.getPayType().equals(PayType.CASH)) {
                hotelFinancialInfo.cash_income = e.getMoney();
            } else {
                hotelFinancialInfo.member_income = e.getMoney();
            }
            return hotelFinancialInfo;
        }).collect(Collectors.groupingBy(e -> e.date)).forEach((key, value) ->
                {
                    FinancialInfo hotelFinancialInfo = new FinancialInfo();
                    hotelFinancialInfo.date = key;
                    value.forEach(e -> {
                        hotelFinancialInfo.cash_income += e.cash_income;
                        hotelFinancialInfo.member_income += e.member_income;
                        hotelFinancialInfo.total_income += (e.cash_income + e.member_income);
                    });
                    result.add(hotelFinancialInfo);
                }
        );
        Collections.sort(result, (e1, e2) -> {
            String date1 = e1.date;
            String date2 = e2.date;
            return date1.compareTo(date2);
        });
        return result;
    }

}
