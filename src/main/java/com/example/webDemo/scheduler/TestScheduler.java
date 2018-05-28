package com.example.webDemo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luofei on 2018/5/23.
 */
@Slf4j
@Component
public class TestScheduler {

    @Scheduled(fixedRate = 60 * 1000L)
    public void testScheduler(){
        log.info("current time={}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }


}
