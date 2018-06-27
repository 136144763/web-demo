package com.example.webDemo.agent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luofei on 2018/6/27.
 */
@Slf4j
public class RealObject implements Interface{
    @Override
    public void doSomething() {
        log.debug("doSomething.");
    }

    @Override
    public void somethingElse(String arg) {
        log.debug("somethingElse {}" + arg);
    }
}
