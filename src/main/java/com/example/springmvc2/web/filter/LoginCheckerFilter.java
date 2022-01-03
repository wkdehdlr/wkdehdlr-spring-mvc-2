package com.example.springmvc2.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginCheckerFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                HttpSession session = httpServletRequest.getSession(false);
                if (session == null) {
                    httpServletResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("LoginCheckerFilter 종료 [{}]", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
