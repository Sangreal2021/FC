package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

// @PhoneNumber 어노테이션 검증용 클래스
// 기본 클래스에서 상속 받고, Alt + Enter로 Override 메소드 생성 후 수정.

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // "2024-02-01T13:00:00" yyyy-MM-ddTHH:mm:ss
        // "2024-02"
        // size = 8

        // 년, 월 까지만 보여주기 위해 조정작업
        var reValue = value+"01";
        var rePattern = pattern+"dd";

        try{
            LocalDate date = LocalDate.parse(reValue, DateTimeFormatter.ofPattern(rePattern));
            System.out.println(date);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
