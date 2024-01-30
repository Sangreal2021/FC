package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @JsonNaming 어노테이션
// UserRequest 에서 Camel Case로 선언해도 Talend API Tester 에서 Snake Case로 쓸 수 있도록 해줌
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    private String userName;

    private Integer userAge;

    private String email;

    // 참조 타입인 Boolean을 써야함
    private Boolean isKorean; // is_korean
}
