package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.MemberShipDao;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * MemberShipDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/11/2017</pre>
 */
public class MemberShipDaoTest {
    MemberShipDao memberShipDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        memberShipDao = ApplicationContextHelper.getApplicationContext().getBean(MemberShipDao
                .class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMaxId()
     */
    @Test
    public void testGetMaxId() throws Exception {
        System.out.println(memberShipDao.getMaxId());
    }


} 
