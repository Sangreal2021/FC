package org.delivery.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {
    
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /*
    * 1. user entity 에서 userId 추출
    * 2. access, refresh 토큰 발행
    * 3. converter 를 통해서 token response 로 변경
    * */
    public TokenResponse issueToken(UserEntity userEntity) {
        
        return Optional.ofNullable(userEntity)
            .map(ue -> {
                return ue.getId();
            })
            .map(userId->{
                var accessToken = tokenService.issueAccessToken(userId);
                var refreshToken = tokenService.issueRefreshToken(userId);
                
                return tokenConverter.toResponse(accessToken, refreshToken);
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
    
    public Long validationAccessToken(String accessToken) {
        var userId = tokenService.validationToken(accessToken);
        return userId;
    }
}
