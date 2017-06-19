package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.service.HotelBusinessConditionService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

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


} 
