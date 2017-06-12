package top.yyf.service;

/**
 * Created by Daniel on 2017/3/20.
 * 需要自动进行的业务操作
 * 自动停止用户的会员、自动暂停用户的会员
 */
public interface AutoService {
    /**
     * 自动停止用户的会员
     * 在用户超过两年未缴费后停止
     */
    void membershipStop();

    /**
     * 自动暂停用户的会员
     * 在用户超过一年未缴费后暂停
     */
    void membershipPause();
}
