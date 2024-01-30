package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("hello")
    public String Hello(){
        // http://localhost:8080/api/hell
        var html = "<html> <body> <h1> Hello Spring boot <h1> </body> </html>";

        return html;
    }

    // http:localhost:8080/api/echo/message
    // http:localhost:8080/api/echo/message
    // http:localhost:8080/api/echo/steve/age/20/is-man/true
    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable(name = "message") String msg,
            @PathVariable int age,
            @PathVariable boolean isMan
    ){
        System.out.println("echo message : " + msg);
        System.out.println("echo age : " + age);
        System.out.println("echo isMan : " + isMan);

        // TODO 대문자로 변환해서 return

        // String 타입의 변수 외에 다른 타입 받아보기

        // boolean, Integer

        return msg.toUpperCase();
    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2024&issued-month=01&issued_day=08
    @GetMapping(path = "/book")
    public void queryParam(
        @RequestParam String category,
        @RequestParam String issuedYear,
        @RequestParam(name = "issued-month") String issuedMonth,
        @RequestParam(name = "issued_day") String issuedDay
    ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issuedDay);
    }

    // http://localhost:8080/api/book2?category=IT&issuedYear=2024&issuedMonth=01&issuedDay=08
    @GetMapping(path = "/book2")
    public void queryParamDto(
        BookQueryParam bookQueryParam
    ){
        System.out.println(bookQueryParam);
    }

    // TODO Parameter 2가지 받음. int 형태로 받아서 두 수의 덧셈, 곱셈
    // TODO String 타입 외 boolean 타입도 받아보기

    @DeleteMapping(path = {
            "/user/{userName}/delete",
            "/user/{userName}/del"
    })
    public void delete(
        @PathVariable String userName
    ){
        log.info("user-name : {}", userName);
    }
}


















