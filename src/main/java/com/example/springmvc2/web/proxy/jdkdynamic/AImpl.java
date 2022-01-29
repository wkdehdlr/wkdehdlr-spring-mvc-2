package com.example.springmvc2.web.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface {

    @Override
    public void call() {
        log.info("A 호출");
    }
}
