package com.example.webDemo;

import com.example.webDemo.exception.ApiResponseException;
import com.example.webDemo.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author luofei on 2018/6/22.
 */
@Slf4j
public class ExceptionTest {

    @Test
    public void testException() {
        String[] sexs = {"男性", "女性", "中性"};
        for (int i = 0; i < sexs.length; i++) {
            if ("中性".equals(sexs[i])) {
                throw new MyException("你全家都是中性！");
            } else {
                log.debug("sex={}",sexs[i]);
            }
        }
    }

    @Test
    public void testApiException() throws ApiResponseException {
        String[] sexs = {"男性", "女性", "中性"};
        for (int i = 0; i < sexs.length; i++) {
            if ("中性".equals(sexs[i])) {
                throw new ApiResponseException("你全家都是中性！");
            } else {
                log.debug("sex={}",sexs[i]);
            }
        }
    }

}
