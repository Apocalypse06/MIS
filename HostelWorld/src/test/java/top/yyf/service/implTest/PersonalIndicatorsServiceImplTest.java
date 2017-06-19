package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.entity.UserMonthlyCostEntity;
import top.yyf.service.PersonalIndicatorsService;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

import java.util.List;

/** 
* PersonalIndicatorsServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 17, 2017</pre> 
* @version 1.0 
*/ 
public class PersonalIndicatorsServiceImplTest { 

    PersonalIndicatorsService personalIndicatorsService;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        personalIndicatorsService = ApplicationContextHelper.getApplicationContext().getBean(PersonalIndicatorsService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: getAllUserMonthlyCost(String month)
    *
    */
    @Test
    public void testGetAllUserMonthlyCost() throws Exception {

        List<UserMonthlyCostEntity> res = personalIndicatorsService.getAllUserMonthlyCost("03");
        for(UserMonthlyCostEntity retMess : res){
            System.out.println(retMess.getUserId()+" "+retMess.getCost()+" "+retMess.getDate());
        }
    }


} 
