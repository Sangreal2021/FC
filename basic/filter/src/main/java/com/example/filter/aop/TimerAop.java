package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class TimerAop {

    // Spring 에서 관리되는 Bean 들에게만 AOP 가 동작함.
    @Pointcut(value = "within(com.example.filter.controller.UserApiController)")
    public void timerPointCut() {

    }

    // 메소드 시작 전
    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");
    }

    // 메소드 종료 후
    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    // 메소드 성공했을 때 결과 값 받을 때 사용
    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("after returning");
    }

    // 예외가 발생했을 때 예외를 잡을 수 있음
    @AfterThrowing(value = "timerPointCut()", throwing = "tx")
    public void afterThrowing(JoinPoint joinPoint, Throwable tx){
        System.out.println("after throwing");
    }

    // 메소드 실행 앞, 뒤로 잡음
    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("메소드 실행 이전[around]");

        Arrays.stream(joinPoint.getArgs()).forEach(it -> {
//            System.out.println(it);
            if(it instanceof UserRequest){
                var tempUser = (UserRequest) it;
                var phoneNumber = tempUser.getPhoneNumber().replace("-", "");
                tempUser.setPhoneNumber(phoneNumber);
            }
        });

        // 암/복호화, 로깅 등의 용도
        var newObjs = Arrays.asList(
                new UserRequest()
        );

        // 서비스 오픈 준비 등 개발시 특정 API 등 이 느릴 때
        // 의심가는 구간 시간체크 용도
        var stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(newObjs.toArray()); // 시간 의심가는 메소드, 예외 발생 메소드

        stopWatch.stop();

        System.out.println("총 소요된 시간 ms = " + stopWatch.getTotalTimeMillis());

        System.out.println("메소드 실행 이후[around]");
    }
}
