package top.yyf.service.impl;

import org.springframework.stereotype.Service;
import top.yyf.service.TokenManager;
import top.yyf.util.AppParameters;
import top.yyf.util.SecurityUtil;

import java.util.HashMap;
import java.util.Map;

import static top.yyf.util.ParaNames.*;

/**
 * Created by Daniel on 2017/3/2.
 */
@Service
public class TokenManagerImpl implements TokenManager {

    @Override
    public String getToken(String username) {
        Map<String, String> kvs = new HashMap<>();
        kvs.put(ID_NAME, username);
        kvs.put(TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        return SecurityUtil.encode(kvs, AppParameters.get(TOKEN));
    }

    @Override
    public String parseToken(String mess) {
        Map<String, String> kvs = SecurityUtil.decodeAsMap(mess, AppParameters.get(TOKEN));
        if (kvs == null)
            return null;
        long timestamp = Long.valueOf(kvs.get(TIMESTAMP));
        if (checkTime(timestamp))
            return kvs.get(ID_NAME);
        else
            return null;
    }

    private boolean checkTime(long timestamp) {
        long time = Long.valueOf(AppParameters.get(TOKEN_TIME));
        if ((timestamp + time) > System.currentTimeMillis())
            return true;
        return false;
    }
}
