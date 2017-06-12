package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.ManagerDao;
import top.yyf.dao.ManagerPayOutDao;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * ManagerPayOutDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/18/2017</pre>
 */
public class ManagerPayOutDaoTest {
    ManagerPayOutDao managerPayOutDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        managerPayOutDao = ApplicationContextHelper.getApplicationContext().getBean
                (ManagerPayOutDao.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUnpaiedInfos()
     */
    @Test
    public void testGetUnpaiedInfos() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUnpaiedInfosByhotel(String hotelId)
     */
    @Test
    public void testGetUnpaiedInfosByhotel() throws Exception {
    }


} 
