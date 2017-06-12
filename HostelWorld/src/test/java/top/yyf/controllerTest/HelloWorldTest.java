package top.yyf.controllerTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.util.HttpRequestUtil;

/**
 * HelloWorld Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>02/28/2017</pre>
 */
public class HelloWorldTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: hello(int id)
     */
    @Test
    public void testHello() throws Exception {
        String request = HttpRequestUtil.sendGet("http://localhost:8080/");
        System.out.println(request);
    }


} 
