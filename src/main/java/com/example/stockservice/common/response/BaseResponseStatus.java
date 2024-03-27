package com.example.stockservice.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(true,HttpStatus.OK.value(), "요청에 성공하였습니다."),

    STOCK_NOT_FOUND(false,HttpStatus.BAD_REQUEST.value(), "재고 정보가 없습니다."),






    /**
     * 500 :  Database, Server 오류
     */

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다.");



    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

}
