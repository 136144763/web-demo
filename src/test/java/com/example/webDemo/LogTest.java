package com.example.webDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/1/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    private static Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
        try {
            int[] counts = {1, 2};
            log.debug("", counts[2]);
        } catch (Exception e) {
            log.error("", e);
            log.info(MarkerFactory.getMarker("errMailer"), e.getMessage());
        }
    }
}
