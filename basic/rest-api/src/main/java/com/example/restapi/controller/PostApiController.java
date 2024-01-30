package com.example.restapi.controller;

import com.example.restapi.model.BookRequest;
import com.example.restapi.model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostApiController {

    // Post방식의 경우 디폴트로 객체를 받아야 함
    // Talend API Tester에 http://localhost:8080/api/post
    @PostMapping("/post")
    public String post(
        // Post or Put 방식에서 http body로 들어오는 데이터를 해당 객체의
        // 데이터 클래스에 매핑을 해주겠다는 의미(여기서는 JSON)
        @RequestBody BookRequest bookRequest
    ){
        System.out.println(bookRequest);
        return bookRequest.toString();
    }

    @PostMapping("/user")
    public UserRequest User(
            @RequestBody UserRequest userRequest
    ){
        System.out.println(userRequest);

        return userRequest;
    }
}
