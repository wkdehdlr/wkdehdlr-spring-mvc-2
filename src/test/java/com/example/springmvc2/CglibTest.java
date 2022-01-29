package com.example.springmvc2;

import com.example.springmvc2.web.proxy.cglib.ConcreteService;
import com.example.springmvc2.web.proxy.cglib.TimeMethodInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {

    @Test
    void cgblid() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        proxy.call();

    }
}