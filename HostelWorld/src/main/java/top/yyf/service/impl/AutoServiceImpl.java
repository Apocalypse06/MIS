package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.MemberShipDao;
import top.yyf.service.AutoService;
import top.yyf.util.MemberShipState;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Daniel on 2017/3/20.
 */
@Service
public class AutoServiceImpl implements AutoService {
    @Autowired
    MemberShipDao memberShipDao;

    @Override
    public void membershipStop() {
        memberShipDao.getAllMemberShip().stream().filter(e ->
                Period.between(LocalDate.parse(e.getLastPayTime()), LocalDate.now())
                        .getYears() >= 2
        ).forEach(e -> {
            e.setState(MemberShipState.PAUSE.toString());
            memberShipDao.saveOrUpdate(e);
        });
    }

    @Override
    public void membershipPause() {
        memberShipDao.getAllMemberShip().stream().filter(e ->
                Period.between(LocalDate.parse(e.getLastPayTime()), LocalDate.now()).getYears() >= 1
        ).forEach(e -> {
            e.setState(MemberShipState.PAUSE.toString());
            memberShipDao.saveOrUpdate(e);
        });
    }

}
