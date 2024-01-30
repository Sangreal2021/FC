package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestApiBController {

    // http://localhost:8080/api/b/hello
    @GetMapping("/hello")
    public void hello(){
        throw new NumberFormatException("number format exception");
    }

    // @ExceptionHandler를 아래에 달아주면 글로벌로 가지 않고 해당 컨트롤러
    // 내에서 해결하게 됨.(@RestControllerAdvice가 있더라도)
/*    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity numberFormatException(
            NumberFormatException e
    ){
        log.error("RestApiBController : ", e);
        return ResponseEntity.ok().build();
    }*/
}
