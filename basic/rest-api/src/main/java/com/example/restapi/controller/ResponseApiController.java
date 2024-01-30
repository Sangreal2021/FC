package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Talend API Tester
// 200, 201, 400 등 지정 가능
// header에 원하는 속성 추가 가능
// body에 JSON방식 데이터 전달 가능

// @RestController
//  - 응답값이 JSON으로 내려가겠다는 선언
// @Controller
//  - html 응답을 하겠다는 선언

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ResponseApiController {

    // http://localhost:8080/api/v1
    @GetMapping("")
    public UserRequest user(){

        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        log.info("user : {}", user);

        var response = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("x-custom", "hi")
                .body(user);

        return user;
    }
}
