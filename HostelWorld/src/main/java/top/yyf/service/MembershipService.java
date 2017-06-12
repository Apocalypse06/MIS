package top.yyf.service;

import top.yyf.mess.retmess.MemberShipRetMess;

/**
 * Created by Daniel on 2017/3/7.
 * 会员相关操作
 * 会员申请、取消会员、获得用户的会员信息、暂停会员、进行会费支付
 */
public interface MembershipService {
    /**
     * 会员申请
     *
     * @param username 用户名
     * @param bankNum  银行卡号
     * @return 是否注册成功
     */
    boolean register(String username, String bankNum);

    /**
     * 取消会员
     *
     * @param username 用户名
     * @return 是否取消成功
     */
    boolean cancel(String username);

    /**
     * 获得用户的会员信息
     * 如果用户不是会员，则返回空值
     *
     * @param username 用户名
     * @return {@link MemberShipRetMess}
     */
    MemberShipRetMess beseInfo(String username);

    /**
     * 暂停会员
     *
     * @param username 用户名
     * @return 是否暂停成功
     */
    boolean pause(String username);

    /**
     * 进行会费支付
     *
     * @param username 用户名
     * @return 是否支付成功
     */
    boolean pay(String username);


}
