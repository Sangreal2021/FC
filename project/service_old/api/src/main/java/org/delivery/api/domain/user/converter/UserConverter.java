package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {
    
    public UserEntity toEntity(UserRegisterRequest request) {
        
        // request 가 null 값이라면 예외 발생
        // -> ApiExceptionHandler 가 잡아서 stack trace 찍어주고
        //    해당 response 가 내려감
        return Optional.ofNullable(request)
            .map(it -> {
                // to entity
                return UserEntity.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .address(request.getAddress())
                    .build();
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }
    
    public UserResponse toResponse(UserEntity userEntity) {
        
        return Optional.ofNullable(userEntity)
            .map(it -> {
                // to response
                return UserResponse.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .status(userEntity.getStatus())
                    .email(userEntity.getEmail())
                    .address(userEntity.getAddress())
                    .registeredAt(userEntity.getRegisteredAt())
                    .unregisteredAt(userEntity.getUnregisteredAt())
                    .lastLoginAt(userEntity.getLastLoginAt())
                    .build();
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
