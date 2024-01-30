package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 진입 전
        log.info(">>>>>> 진입 ");
        // ContentCachingRequestWrapper
        //  - 리더로 읽을 때 따로 내부에다 ContentCaching byte array에 해당 내용을 담아 놓음
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        var reqJson = new String(req.getContentAsByteArray());
        log.info("req : {}", reqJson);

        var resJson = new String(res.getContentAsByteArray());
        log.info("res : {}", reqJson);

        log.info("<<<<<< 리턴 ");
        // 진입 후

        // TAT에서 body가 비는 것을 방지하기 위해 반드시 마지막에 호출
        res.copyBodyToResponse();
    }
}
