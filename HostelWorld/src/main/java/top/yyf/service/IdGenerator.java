package top.yyf.service;

/**
 * Created by Daniel on 2017/3/11.
 * id生成器
 * 生成下一个用户id、生成下一个酒店id
 */
public interface IdGenerator {
    /**
     * 生成下一个用户id
     *
     * @return
     */
    String generateNextMembershipId();

    /**
     * 生成下一个酒店id
     *
     * @return
     */
    String generateNextHotelId();
}
