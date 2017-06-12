package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.LevelDao;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * LevelDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/11/2017</pre>
 */
public class LevelDaoTest {
    LevelDao levelDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        levelDao = ApplicationContextHelper.getApplicationContext().getBean(LevelDao.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getLevelByScore(int score)
     */
    @Test
    public void testGetLevelByScore() throws Exception {
        System.out.println(levelDao.getLevelByScore(500));
    }


} 
