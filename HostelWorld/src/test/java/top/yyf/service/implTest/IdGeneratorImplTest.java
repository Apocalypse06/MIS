package top.yyf.service.implTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.service.IdGenerator;
import top.yyf.util.ApplicationContextHelper;
import top.yyf.util.BeforeTest;

/**
 * IdGeneratorImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/11/2017</pre>
 */
public class IdGeneratorImplTest {
    IdGenerator idGenerator;

    @Before
    public void before() throws Exception {
        BeforeTest.beforeTest();
        idGenerator = ApplicationContextHelper.getApplicationContext().getBean(IdGenerator.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: generateNextMembershipId()
     */
    @Test
    public void testGenerateNextMembershipId() throws Exception {
        System.out.println(idGenerator.generateNextMembershipId());
    }

    /**
     * Method: generateNextHotelId()
     */
    @Test
    public void testGenerateNextHotelId() throws Exception {
        System.out.println(idGenerator.generateNextHotelId());
    }


} 
