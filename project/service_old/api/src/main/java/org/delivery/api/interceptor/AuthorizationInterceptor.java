package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    
    private final TokenBusiness tokenBusiness;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
        log.info("Authorization Interceptor : {}", request.getRequestURI());
        
        // WEB 즉 Chrome의 경우 GET, POST등 을 요청하기 전 OPTION API를 요청을 해서
        // 해당 메소드를 지원하는지 체크. = 통과
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        
        // js, html, png 등 resource 를 요청하는 경우 통과
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        
        // TODO Header 검증
        var accessToken = request.getHeader("authorization-token");
        if(accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }
        
        var userId = tokenBusiness.validationAccessToken(accessToken);
        
        if(userId != null) {
            // 한가지 요청에 대해 유효하게 글로벌하게 저장할 수 있는 영역
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true;    // 인증 성공
        }
        
        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");
    }
}










