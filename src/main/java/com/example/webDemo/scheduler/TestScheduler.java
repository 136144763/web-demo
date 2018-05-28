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

    /**
     * 第一次调用延时30秒，每秒执行一次
     */
    @Scheduled(initialDelay = 30 * 1000, fixedRate = 1000)
    public void testScheduler() {
        log.info("current time={}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void testSchedulerByCorn() {
        log.info("current time={}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }


}
