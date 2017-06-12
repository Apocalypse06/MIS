package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.CheckInDao;
import top.yyf.entity.CheckInEntity;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * CheckInDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/16/2017</pre>
 */
public class CheckInDaoTest {
    CheckInDao checkInDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        checkInDao = ApplicationContextHelper.getApplicationContext().getBean(CheckInDao.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getCheckInByRoomId(Integer roomId)
     */
    @Test
    public void testGetCheckInByRoomId() throws Exception {
        CheckInEntity checkInEntity = checkInDao.getCheckInByRoomId(3);
        System.out.println(checkInEntity == null);
    }


} 
