package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서버에 어떠한 정보가 오가는지 확인할 수 있는 필터 로그

@Component
@Slf4j
public class LoggerFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        
        chain.doFilter(req, res);
        
        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();
        
        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);
            
            // authorization-token : ???, user-agent : ???
            headerValues
                .append("[")
                .append(headerKey)
                .append(" : ")
                .append(headerValue)
                .append("] ");
        });
        
        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();
        
        log.info(">>>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);
        
        
        // response 정보
        var responseHeaderValues = new StringBuilder();
        
        res.getHeaderNames().forEach(headerKey->{
            var headerValue = res.getHeader(headerKey);
            
            responseHeaderValues
                .append("[")
                .append(headerKey)
                .append(" : ")
                .append(headerValue)
                .append("] ");
        });
        
        var responseBody = new String(res.getContentAsByteArray());
        log.info(">>>>> uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);
        
        
        // 아래 코드는 반드시 필수!! (없으면 responseBody 가 비워져서 감)
        res.copyBodyToResponse();
    }
}


















