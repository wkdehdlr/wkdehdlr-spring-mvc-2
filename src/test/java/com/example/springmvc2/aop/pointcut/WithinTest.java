package com.example.springmvc2.aop.pointcut;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.springmvc2.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        log.info("helloMethod = {}", helloMethod);
    }

    @Test
    void withinMatch() {
        pointcut.setExpression("within(com.example.springmvc2.aop.member.MemberServiceImpl)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(com.example.springmvc2.aop.member.*Service*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void within_서브패키지() {
        pointcut.setExpression("within(com.example.springmvc2.aop..*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void 부모_타입_적용_안된다_반드시_해당_타입에만_적용() {
        pointcut.setExpression("within(com.example.springmvc2.aop.member.MemberService)");
        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

}
