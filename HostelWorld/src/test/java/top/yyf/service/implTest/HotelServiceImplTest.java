package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.mess.input.HotelRegister;
import top.yyf.service.HotelService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * HotelServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/13/2017</pre>
 */
public class HotelServiceImplTest {
    HotelService hotelService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        hotelService = ApplicationContextHelper.getApplicationContext().getBean(HotelService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: register(HotelRegister hotelRegister)
     */
    @Test
    public void testRegister() throws Exception {
        HotelRegister hotelRegister = new HotelRegister();
        hotelRegister.setName("test");
        hotelRegister.setDescription("这是测试");
        hotelRegister.setAddress("江苏省南京市栖霞区");
        hotelRegister.setPassword("123456");
        hotelRegister.setConfirmPass("123456");
        hotelService.register(hotelRegister);
    }

    /**
     * Method: login(String hotelId, String password)
     */
    @Test
    public void testLogin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: hotelInfos(HotelQuery hotelQuery)
     */
    @Test
    public void testHotelInfos() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getHotelInfo(String hotelId)
     */
    @Test
    public void testGetHotelInfo() throws Exception {
//TODO: Test goes here... 
    }


} 
