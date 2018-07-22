package com.example.webDemo;

import com.example.webDemo.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author luofei on 2018/6/22.
 */
@Slf4j
public class HttpClientTest {

    @Test
    public void testClientHttp() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n>>> method=").append("").append(",input=").append("");

    }

    @Test
    public void testAES() throws Exception {
        String password = "1";
        String passwordEncrypt = AESUtil.getInstance().encrypt(password);
        String passwordDecrypt = AESUtil.getInstance().decrypt(passwordEncrypt);
        log.debug("passwordEncrypt={}", passwordEncrypt);
        log.debug("passwordDecrypt={}",passwordDecrypt);
    }
}
