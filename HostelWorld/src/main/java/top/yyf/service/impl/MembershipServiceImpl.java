package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.LevelDao;
import top.yyf.dao.MemberShipDao;
import top.yyf.dao.UserDao;
import top.yyf.entity.MembershipEntity;
import top.yyf.entity.UserEntity;
import top.yyf.mess.retmess.MemberShipRetMess;
import top.yyf.service.IdGenerator;
import top.yyf.service.MembershipService;
import top.yyf.util.MemberShipState;

import java.time.LocalDate;

/**
 * Created by Daniel on 2017/3/11.
 */
@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    MemberShipDao memberShipDao;
    @Autowired
    UserDao userDao;
    @Autowired
    IdGenerator idGenerator;
    @Autowired
    LevelDao levelDao;

    @Override
    public boolean register(String username, String bankNum) {
        UserEntity userEntity = userDao.get(username);
        if (userEntity == null || userEntity.getMerbership() != null)
            return false;
        userEntity.setBankNum(bankNum);
        MembershipEntity membershipEntity = new MembershipEntity();
        membershipEntity.setTotalscore(0);
        membershipEntity.setMerbershipId(idGenerator.generateNextMembershipId());
        membershipEntity.setCreateTime(LocalDate.now().toString());
        userEntity.setMerbership(membershipEntity);
        membershipEntity.setUser(userEntity);
        membershipEntity.setLastPayTime(LocalDate.now().toString());
        membershipEntity.setTotalscore(0);
        membershipEntity.setAvailablescore(0);
        membershipEntity.setLevel(levelDao.get((byte) 1));
        membershipEntity.setState(MemberShipState.NORMAL.toString());
        try {
            memberShipDao.saveOrUpdate(membershipEntity);
            userDao.saveOrUpdate(userEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancel(String username) {
        MembershipEntity membershipEntity = memberShipDao.getMembershipByUserName(username);
        try {
            memberShipDao.delete(membershipEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MemberShipRetMess beseInfo(String username) {
        MembershipEntity membershipEntity = memberShipDao.getMembershipByUserName(username);
        if (membershipEntity == null)
            return null;
        MemberShipRetMess memberShipRetMess = new MemberShipRetMess();
        memberShipRetMess.carId = membershipEntity.getMerbershipId();
        memberShipRetMess.openDate = membershipEntity.getCreateTime();
        memberShipRetMess.rank = membershipEntity.getLevel().getLevel();
        memberShipRetMess.score = membershipEntity.getTotalscore();
        memberShipRetMess.state = MemberShipState.valueOf(membershipEntity.getState());
        memberShipRetMess.lastPayDate = membershipEntity.getLastPayTime();
        memberShipRetMess.availableScore = membershipEntity.getAvailablescore();
        return memberShipRetMess;
    }

    @Override
    public boolean pause(String username) {
        MembershipEntity membershipEntity = memberShipDao.getMembershipByUserName(username);
        try {
            membershipEntity.setState(MemberShipState.PAUSE.toString());
            memberShipDao.saveOrUpdate(membershipEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean pay(String username) {
        MembershipEntity membershipEntity = memberShipDao.getMembershipByUserName(username);
        try {
            membershipEntity.setState(MemberShipState.NORMAL.toString());
            memberShipDao.saveOrUpdate(membershipEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
