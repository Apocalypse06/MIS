package top.yyf.service.implTest;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.yyf.service.TokenManager;
import top.yyf.service.impl.TokenManagerImpl;

import static top.yyf.util.AppParameters.*;
import static top.yyf.util.ParaNames.TOKEN;
import static top.yyf.util.ParaNames.TOKEN_TIME;

/**
 * TokenManagerImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/02/2017</pre>
 */
public class TokenManagerImplTest {
    TokenManager tokenManager;

    @Before
    public void before() throws Exception {
        put(TOKEN, "token");
        put(TOKEN_TIME, "300000");
        tokenManager = new TokenManagerImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getToken(String username)
     */
    @Test
    public void testGetToken() throws Exception {
        String token = tokenManager.getToken("yyf");
        System.out.println(token);

        String username = tokenManager.parseToken(token);
        System.out.println(username);
        Assert.assertEquals(username,"yyf");
    }

    /**
     * Method: parseToken(String mess)
     */
    @Test
    public void testParseToken() throws Exception {
//TODO: Test goes here... 
    }


} 
