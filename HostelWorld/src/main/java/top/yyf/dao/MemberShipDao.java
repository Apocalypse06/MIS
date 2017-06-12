package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.MembershipEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class MemberShipDao extends BaseDao<MembershipEntity, String> {
    public String getMaxId() {
        List li = getSession().createQuery("from MembershipEntity order by id " +
                "desc")
                .setMaxResults
                        (1).list();
        if (li == null || li.size() == 0)
            return null;
        return ((MembershipEntity) li.get(0)).getMerbershipId();
    }

    public MembershipEntity getMembershipByUserName(String username) {
        return getByHQL("from MembershipEntity where user.id=?", username);
    }

    public List<MembershipEntity> getAllMemberShip() {
        return getListByHQL("from MembershipEntity");
    }

}
