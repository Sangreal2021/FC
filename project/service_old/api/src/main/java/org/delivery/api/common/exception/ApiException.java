package org.delivery.api.common.exception;

// ApiException은 우리가 만든 에러코드와 묶어서 사용할 예정.

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs {
    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;
    
    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription()); // 받자마자 부모(RuntimeException)에게 메세지 던짐
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }
    
    // errorDescription 을 직접 넘겨줌
    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorDescription); // 내가 정의한 메세지를 지정
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
    
    // 예외가 터질수도 있으므로 에러코드와 예외를 던짐
    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }
    
    // 모든 것을 다 받을 수 있는 생성자
    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorDescription){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription; // 내가 원하는 메세지
    }
}

















