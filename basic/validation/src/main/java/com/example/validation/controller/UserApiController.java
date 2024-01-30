package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @PostMapping("")
    public Api<UserRegisterRequest> register(
            // @Valid - 요청이 들어올 때 해당 클래스에 대한 validation을 자동으로 검증
            @Valid
            @RequestBody
            Api<UserRegisterRequest> userRegisterRequest
    ){
        log.info("init : {}", userRegisterRequest);

        var body = userRegisterRequest.getData();

        var response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();

        return response;
    }
}
