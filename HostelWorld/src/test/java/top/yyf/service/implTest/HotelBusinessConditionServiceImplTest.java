package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.mess.retmess.HotelTurnoverIncrease;
import top.yyf.mess.retmess.HotelTurnoverMess;
import top.yyf.service.HotelBusinessConditionService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

import java.util.List;

/** 
* HotelBusinessConditionServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 18, 2017</pre> 
* @version 1.0 
*/ 
public class HotelBusinessConditionServiceImplTest {
    HotelBusinessConditionService hotelBusinessConditionService;

@Before
public void before() throws Exception {
    BeforeTest.beforeTest();
    hotelBusinessConditionService = ApplicationContextHelper.getApplicationContext().getBean
            (HotelBusinessConditionService.class);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getADR(String hotel, String month_quanter) 
* 
*/ 
@Test
public void testGetADR() throws Exception {
    hotelBusinessConditionService.getADR("h000001", "03");
}

/** 
* 
* Method: getOCC(String hotel, String month_quanter) 
* 
*/ 
@Test
public void testGetOCC() throws Exception {

    hotelBusinessConditionService.getOCC("h000001", "06");
}

/** 
* 
* Method: getREVPAR(String hotel, String month_quanter) 
* 
*/ 
@Test
public void testGetREVPAR() throws Exception {
    hotelBusinessConditionService.getREVPAR("h000001", "06");
}

@Test
public void testGetBusinessInfos() throws Exception {
    hotelBusinessConditionService.getBusinessInfos("h000001");
}

@Test
public void testGetPlaceNumber() throws Exception {
    hotelBusinessConditionService.getPlaceNumber("h000001");
}

@Test
public void testGetAgeNumber() throws Exception{
    hotelBusinessConditionService.getAgeNumber("h000001");
}

@Test
    public void testGetHotelTurnoverMess() throws Exception {
    List<HotelTurnoverMess> hotelTurnoverMessList = hotelBusinessConditionService.getHotelTurnoverMess();
    for(HotelTurnoverMess hotelTurnoverMess:hotelTurnoverMessList){
        System.out.println(hotelTurnoverMess.hotel+" "+hotelTurnoverMess.joinDate+" "+hotelTurnoverMess.city
        +" "+hotelTurnoverMess.currentMonth+" "+hotelTurnoverMess.currentTurnover);
    }
}

@Test
public void testgGtHotelTurnoverMessById() throws Exception {
    List<HotelTurnoverIncrease> hotelTurnoverIncreaseList = hotelBusinessConditionService.getHotelTurnoverMessById("h000001");

    for(HotelTurnoverIncrease hotelTurnoverIncrease:hotelTurnoverIncreaseList){
        System.out.println(hotelTurnoverIncrease.month+" "+ hotelTurnoverIncrease.increase+" "+hotelTurnoverIncrease.turnover);
    }
}


} 
