package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.mess.input.ReservationInfo;
import top.yyf.service.ReservationService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * ReservationServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/13/2017</pre>
 */
public class ReservationServiceImplTest {
    ReservationService reservationService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        reservationService = ApplicationContextHelper.getApplicationContext().getBean
                (ReservationService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getReservationInfosByUser(String username)
     */
    @Test
    public void testGetReservationInfosByUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getReservationInfosByHotel(String hotelId)
     */
    @Test
    public void testGetReservationInfosByHotel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: bookRoom(String username, ReservationInfo reservationInfo)
     */
    @Test
    public void testBookRoom() throws Exception {
        ReservationInfo reservationInfo = new ReservationInfo();
        reservationInfo.setFromDate("2017-03-01");
        reservationInfo.setToDate("2017-03-03");
        reservationInfo.setName("杨雁飞");
        reservationInfo.setRoomType("单人间");
        reservationInfo.setPhoneNum("123");
        System.out.println(reservationService.bookRoom("835168932@qq.com", reservationInfo));
    }

    /**
     * Method: cancelReservation(String username, Integer reservationId)
     */
    @Test
    public void testCancelReservation() throws Exception {
        reservationService.cancelReservation("835168932@qq.com", 2);
    }


} 
