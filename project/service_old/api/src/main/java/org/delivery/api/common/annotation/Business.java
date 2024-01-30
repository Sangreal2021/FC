package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Service
//  Spring 에서 자동으로 이 어노테이션 감지 및 @Business 어노테이션 달린 클래스들은
//  자동으로 Bean 으로 등록됨

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface Business {
 
    @AliasFor(annotation = Service.class)
    String value() default "";
}
