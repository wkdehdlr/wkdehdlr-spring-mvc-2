package com.example.springmvc2.aop.member;

import com.example.springmvc2.aop.member.annotation.ClassAop;
import com.example.springmvc2.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Service;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }

}
