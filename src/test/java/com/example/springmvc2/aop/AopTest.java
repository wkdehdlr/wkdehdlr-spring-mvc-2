package com.example.springmvc2.aop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springmvc2.aop.aspect.AspectV1;
import com.example.springmvc2.aop.aspect.AspectV2;
import com.example.springmvc2.aop.aspect.AspectV3;
import com.example.springmvc2.aop.aspect.AspectV5Order;
import com.example.springmvc2.aop.order.OrderRepository;
import com.example.springmvc2.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
//@Import(AspectV2.class)
@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
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
