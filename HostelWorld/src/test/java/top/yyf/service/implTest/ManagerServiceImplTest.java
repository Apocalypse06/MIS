package top.yyf.service.implTest;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.service.ManagerService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * ManagerServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/17/2017</pre>
 */
public class ManagerServiceImplTest {
    ManagerService managerService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        managerService = ApplicationContextHelper.getApplicationContext().getBean(ManagerService
                .class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: passHotel(String hotelId)
     */
    @Test
    public void testPassHotel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: payOut(List<Integer> payOrders)
     */
    @Test
    public void testPayOut() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUnPaiedInfos()
     */
    @Test
    public void testGetUnPaiedInfos() throws Exception {
        System.out.println(managerService.payOut("h000001"));
    }


} 
