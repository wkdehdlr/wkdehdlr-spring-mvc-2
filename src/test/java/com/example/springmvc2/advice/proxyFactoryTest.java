package com.example.springmvc2.advice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.springmvc2.web.proxy.advice.ServiceImpl;
import com.example.springmvc2.web.proxy.advice.ServiceInterface;
import com.example.springmvc2.web.proxy.cglib.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class proxyFactoryTest {

    @Test
    void 인터페이스가_있으면_JDK_동적_프록시_사용() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
        proxy.save();
        proxy.find();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isJdkDynamicProxy(proxy));
        assertFalse(AopUtils.isCglibProxy(proxy));
    }

    @Test
    void 구체_클래스만_있으면_CGLIB_사용() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
        proxy.call();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
    }

    @Test
    void ProxyTargetClass_옵션을_사용하면_인터페이스가_있어도_CGLIB_사용() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
        proxy.save();
        proxy.find();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
    }
}
