package com.example.validation.annotation;

import com.example.validation.validator.PhoneNumberValidator;
import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 필드있는 요소 대상, 실행중에만 적용

@Constraint(validatedBy = {YearMonthValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank
public @interface YearMonth {
    String message() default "년,월 양식에 맞지 않습니다. ex) 20240101";
    String pattern() default "yyyyMM";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
