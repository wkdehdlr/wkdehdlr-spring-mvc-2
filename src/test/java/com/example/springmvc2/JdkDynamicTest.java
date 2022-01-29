package com.example.springmvc2;

import com.example.springmvc2.web.proxy.jdkdynamic.AImpl;
import com.example.springmvc2.web.proxy.jdkdynamic.AInterface;
import com.example.springmvc2.web.proxy.jdkdynamic.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

public class JdkDynamicTest {

    @Test
    void dynamic() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(
            AInterface.class.getClassLoader(),
            new Class[]{AInterface.class},
            handler);

        proxy.call();
    }
}