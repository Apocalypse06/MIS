package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.service.MembershipService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * MembershipServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/11/2017</pre>
 */
public class MembershipServiceImplTest {
    MembershipService membershipService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        membershipService = ApplicationContextHelper.getApplicationContext().getBean
                (MembershipService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: register(String username, String bankNum)
     */
    @Test
    public void testRegister() throws Exception {
        membershipService.register("835168932@qq.com", "123456");
    }

    /**
     * Method: cancel(String username)
     */
    @Test
    public void testCancel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: beseInfo(String username)
     */
    @Test
    public void testBeseInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: pause(String username)
     */
    @Test
    public void testPause() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: pay(String username)
     */
    @Test
    public void testPay() throws Exception {
//TODO: Test goes here... 
    }


} 
