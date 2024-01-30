package com.example.memorydb.config;

import com.example.memorydb.user.db.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// - 스프링앱이 실행될 때 configuration 을 찾아서 특정 내용들을 Spring Context 라는 영역에다
//  new 생성자를 통해 객체를 만듬.
// - 그리고 사용하고자 하는 Service 또는 Controller 또는 각각의 bean 으로 만들어진 영역들 사이에서
//  여기에 대한 내용이 필요하면 spring 이 알아서 주입해줌.

//@Configuration
//public class DataBaseConfig {
//
//    @Bean
//    public UserRepository userRepository(){
//        return new UserRepository();
//    }
//}
