package com.example.springmvc2.web.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
}
