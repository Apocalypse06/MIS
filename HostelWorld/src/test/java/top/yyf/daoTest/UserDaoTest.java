package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.ManagerDao;
import top.yyf.entity.ManagerEntity;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * UserDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>02/27/2017</pre>
 */
public class UserDaoTest {
    ManagerDao userDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        userDao = ApplicationContextHelper.getApplicationContext().getBean(ManagerDao.class);
    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testAddManager() throws Exception {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setUsername("yyf");
        managerEntity.setPassword("123");
        userDao.saveOrUpdate(managerEntity);
    }


} 
