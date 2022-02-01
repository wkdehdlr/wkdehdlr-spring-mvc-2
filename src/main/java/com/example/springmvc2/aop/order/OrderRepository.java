package com.example.springmvc2.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemId) {
        log.info("[orderRepository] 실행");
        if ("ex".equals(itemId)) {
            throw new IllegalArgumentException("예외 발생!");
        }
        return "ok";
    }
}
