package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

// 로그 수집 클래스

@Component
@Slf4j
public class LoggerFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request); // 형변환
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response); // 형변환
        
        log.info("INIT URI : {}" , req.getRequestURI());
        chain.doFilter(req, res);
        
        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();
        
        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey);
            
            // authorization-token : ??? , user-agent : ???
            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });
        
        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();
        
        log.info(">>>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);
        
        
        // response 정보
        var responseHeaderValues = new StringBuilder();
        
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            
            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });
        
        var responseBody = new String(res.getContentAsByteArray());
        
        log.info("<<<<< uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);
        
        // responseBody의 내용을 읽었으므로 다시 초기화 시켜주는 코드
        // 아래 코드 없으면 responseBody가 비워져서 감
        res.copyBodyToResponse();
    }
}














