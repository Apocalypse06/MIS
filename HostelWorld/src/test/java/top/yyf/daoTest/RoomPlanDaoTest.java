package top.yyf.daoTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.dao.RoomPlanDao;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * RoomPlanDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/14/2017</pre>
 */
public class RoomPlanDaoTest {
    RoomPlanDao roomPlanDao;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        roomPlanDao = ApplicationContextHelper.getApplicationContext().getBean(RoomPlanDao.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getRoomPlansByHotelId(String hotelId)
     */
    @Test
    public void testGetRoomPlansByHotelId() throws Exception {
    }

    /**
     * Method: getRoomPlanByName(String roomType, String hotelId)
     */
    @Test
    public void testGetRoomPlanByName() throws Exception {
        System.out.println(roomPlanDao.getRoomPlanByName("单人间", "h000001").getRpId());
    }


} 
