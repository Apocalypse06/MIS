package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.LevelInfoEntity;

import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class LevelDao extends BaseDao<LevelInfoEntity, Byte> {
    public int getLevelByScore(int score) {
        List li = getSession().createQuery("from LevelInfoEntity l where ?>=l.demandScore order " +
                "by demandScore desc ").setParameter(0, score).setMaxResults(1).list();
        if (li == null || li.size() == 0)
            return -1;
        return ((LevelInfoEntity) li.get(0)).getLevel();
    }
}
