package com.example.springmvc2.web.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorPageController {

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        log.info("errorPage404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("errorPage500");
        log.info("{}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        log.info("{}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
        log.info("{}", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        log.info("{}", request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME));
        log.info("{}", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request,
        HttpServletResponse response) {
        log.info("API error 500 JSON");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        result.put("status", code);
        result.put("message", ex.getMessage());

        return new ResponseEntity<>(result, HttpStatus.valueOf(code));
    }
}
