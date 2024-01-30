package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 일반적인 에러 발생 예시

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "")
    public void hello(){
        var list = List.of("hello");
        var element = list.get(1);

//      throw new RuntimeException("run time exception call");
        log.info("element : {}", element);
    }
}
