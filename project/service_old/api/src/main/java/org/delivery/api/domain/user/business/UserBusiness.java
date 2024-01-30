package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;

@Business
@RequiredArgsConstructor
public class UserBusiness {
    
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;
    
    /*
    * 사용자에 대한 가입처리 로직
    * 1. request 데이터 -> entity
    * 2. entity -> save
    * 3. save 된 entity -> response
    * 4. response 를 리턴
    * */
    public UserResponse register(UserRegisterRequest request) {
        
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
        
        // 위의 코드를 람다식으로 변환
/*        return Optional.ofNullable(request)
            .map(userConverter::toEntity)
            .map(userService::register)
            .map(userConverter::toResponse)
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));*/
    }
    
    /*
    * 1. email, password 를 가지고 사용자 체크
    * 2. user entity 떨어지면 로그인 확인
    * 3. token 생성
    * 4. token 을 response 로 내려줌
    * */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 사용자 없으면 throw
        
        // (TODO) 사용자 존재 -> 토큰 생성 로직으로 변경하기
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        
        return tokenResponse;
    }
    
    public UserResponse me(
        User user
    ) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        
        return response;
    }
}
