package com.example.springmvc2.aop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springmvc2.aop.order.OrderRepository;
import com.example.springmvc2.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AopTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private  OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isApoProxy, orderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isApoProxy, orderRepository = {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThrows(IllegalArgumentException.class, () -> orderService.orderItem("ex"));
    }
}
