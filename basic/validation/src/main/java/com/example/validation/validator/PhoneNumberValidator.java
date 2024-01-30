package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

// @PhoneNumber 어노테이션 검증용 클래스
// 기본 클래스에서 상속 받고, Alt + Enter로 Override 메소드 생성 후 수정.

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private String regexp;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean result = Pattern.matches(regexp, value);
        return result;
    }
}
