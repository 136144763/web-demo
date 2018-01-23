package com.example.webDemo;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
        try {
            int[] counts = {1, 2};
            logger.debug("", counts[2]);
        } catch (Exception e) {
            logger.error("", e);
            log.info(MarkerFactory.getMarker("errMailer"), e.getMessage());
        }
    }
}
