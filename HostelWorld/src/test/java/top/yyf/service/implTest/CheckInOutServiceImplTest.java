package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.mess.input.CheckIn;
import top.yyf.mess.input.Person;
import top.yyf.service.CheckInOutService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;
import top.yyf.util.PayType;

import java.util.Arrays;

/**
 * CheckInOutServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/16/2017</pre>
 */
public class CheckInOutServiceImplTest {
    CheckInOutService checkInOutService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        checkInOutService = ApplicationContextHelper.getApplicationContext().getBean
                (CheckInOutService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: checkIn(CheckIn checkIn)
     */
    @Test
    public void testCheckIn() throws Exception {
        CheckIn checkIn = new CheckIn();
        checkIn.setReservationId(5);
        checkIn.setPayType(PayType.MEMBERCARD);
        checkIn.setRoomId(3);
        Person person1 = new Person();
        person1.setName("yyf");
        person1.setIdNum("123");
        Person person2 = new Person();
        person2.setName("tzh");
        person2.setIdNum("123456");
        checkIn.setPersons(Arrays.asList(person1, person2));
        checkInOutService.checkIn(checkIn);

    }

    /**
     * Method: checkOut(Integer roomId)
     */
    @Test
    public void testCheckOut() throws Exception {
    }


} 
