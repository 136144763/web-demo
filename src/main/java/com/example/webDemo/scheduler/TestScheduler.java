package com.example.webDemo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luofei on 2018/5/23.
 */
@Slf4j
@Component
public class TestScheduler {

    @Value("${app.fetch.current.day}")
    private boolean flag;

//    /**
//     * 第一次调用延时30秒，每秒执行一次
//     */
//    @Scheduled(initialDelay = 30 * 1000, fixedRate = 1000)
//    public void testScheduler() {
//       if(flag){
//           prientCurrentTime();
//       }
//    }

    /**
     * 每天三点执行
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void testSchedulerByCorn() {
        if (flag) {
            prientCurrentTime();
        }
    }

    public void prientCurrentTime() {
        log.info("current time={}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }


}
