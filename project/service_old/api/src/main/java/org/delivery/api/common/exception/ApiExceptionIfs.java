package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIfs;

// 아래 두가지 메소드는 우리가 쓰는 커스텀 exception 에서는 반드시
// 정의해야 됨

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
