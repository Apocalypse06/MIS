package top.yyf.service.implTest;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.mess.input.RoomPlan;
import top.yyf.mess.input.RoomQuery;
import top.yyf.service.RoomService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * RoomServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/13/2017</pre>
 */
public class RoomServiceImplTest {
    RoomService roomService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        roomService = ApplicationContextHelper.getApplicationContext().getBean(RoomService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getRoomInfos(String hotelId, RoomQuery roomQuery)
     */
    @Test
    public void testGetRoomInfos() throws Exception {
        System.out.println(new Gson().toJson(roomService.getRoomInfos("h000001",new RoomQuery())));
    }

    /**
     * Method: getRoomTypes(String hotelId)
     */
    @Test
    public void testGetRoomTypes() throws Exception {
        System.out.println(new Gson().toJson(roomService.getRoomTypes("h000001")));
    }

    /**
     * Method: addRoomPlan(String hotelId, RoomPlan roomPlan)
     */
    @Test
    public void testAddRoomPlan() throws Exception {
        RoomPlan roomPlan = new RoomPlan();
        roomPlan.setType("大床房");
        roomPlan.setDes("这是测试");
        roomPlan.setAccommodations(2);
        roomPlan.setStartDate("2017-03-01");
        roomPlan.setEndDate("2017-03-17");
        roomPlan.setPrice(125.5);
        roomService.addRoomPlan("h000001", roomPlan);
    }

    /**
     * Method: addRoomPlan(String hotelId, RoomPlan roomPlan)
     */
    @Test
    public void testAddActualRoom() throws Exception {
        roomService.addActualRoom("h000001", 1, "H002");
    }





} 
