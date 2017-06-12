package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.CheckInEntity;

/**
 * Created by Daniel on 2017/2/27.
 * 房间入住
 */
@Repository
public class CheckInDao extends BaseDao<CheckInEntity, Integer> {
    /**
     * 根据房间id获得入住编号
     *
     * @param roomId 房间id
     * @return 入住信息
     */
    public CheckInEntity getCheckInByRoomId(Integer roomId) {
        return getByHQL("from CheckInEntity where roomNum.id=? AND isCheckedOut = 0 ", roomId);
    }
}
