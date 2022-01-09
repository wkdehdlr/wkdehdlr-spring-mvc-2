package com.example.springmvc2.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v2")
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/member/{id}")
    public MemberDto getMember(@PathVariable String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("예외 발생!!");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("예외 발생!!");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "홍길동");
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {

        private String memberId;
        private String name;
    }
}
