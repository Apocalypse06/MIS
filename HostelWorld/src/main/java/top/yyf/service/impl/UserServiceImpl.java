package top.yyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yyf.dao.UserDao;
import top.yyf.entity.UserEntity;
import top.yyf.mess.input.UserRegister;
import top.yyf.mess.retmess.UserRetMess;
import top.yyf.service.UserService;
import top.yyf.util.UserType;

/**
 * Created by Daniel on 2017/3/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean register(UserRegister userRegister) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRegister.getEmail());
        userEntity.setName(userRegister.getUsername());
        userEntity.setUserType(UserType.NORMAL);
        userEntity.setPassword(userRegister.getPassword());
        try {
            userDao.save(userEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean login(String username, String password) {
        UserEntity userEntity = userDao.get(username);
        return userEntity != null && userEntity.getPassword().equals(password);
    }

    @Override
    public boolean isExisted(String username) {
        return userDao.get(username) != null;
    }

    @Override
    public UserRetMess getUserInfo(String username) {
        UserEntity userEntity = userDao.get(username);
        if (userEntity == null)
            return null;
        UserRetMess userRetMess = new UserRetMess();
        userRetMess.email = userEntity.getEmail();
        userRetMess.name = userEntity.getName();
        userRetMess.bankNum = userEntity.getBankNum();
        return userRetMess;
    }
}
