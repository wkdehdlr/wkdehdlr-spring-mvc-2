package com.example.springmvc2.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.springmvc2.aop..*(..))")
    public void allOrder() {
    } // pointcut signature

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}

}
