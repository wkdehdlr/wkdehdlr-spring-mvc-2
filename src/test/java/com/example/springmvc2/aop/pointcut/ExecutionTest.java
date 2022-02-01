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
public class ExecutionTest {

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
    void exactMatch() {
        // public java.lang.String com.example.springmvc2.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String com.example.springmvc2.aop.member.MemberServiceImpl.hello(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePatternMatch() {
        pointcut.setExpression("execution(* hel*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePatternMatch2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePatternMatch3() {
        pointcut.setExpression("execution(* nono(..))");
        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePackageMatch() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.member.MemberServiceImpl.hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePackageMatch2() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.member.*.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePackageMatch3() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.*.*.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void namePackageMatch4() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop..*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.member.MemberServiceImpl.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void typeMatchSuper() {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.member.MemberService.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void 부모_타입에_있는_메소드만_허용() throws NoSuchMethodException {
        pointcut.setExpression("execution(* com.example.springmvc2.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertFalse(pointcut.matches(internalMethod, MemberServiceImpl.class));
    }

    @Test
    void String_타입_파라미터_허용() {
        pointcut.setExpression("execution(* *(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void 파라미터가_없어야한다() {
        pointcut.setExpression("execution(* *())");
        assertFalse(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void 정확히_하나의_파라미터_허용_모든_타입_허용() {
        pointcut.setExpression("execution(* *(*))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void 파라미터_숫자와_무관하게_모든_파라미터_모든_타입_허용() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }

    @Test
    void String_타입으로_시작하고_숫자와_무관하게_모든_파라미터_모든_타입_허용() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceImpl.class));
    }
}
