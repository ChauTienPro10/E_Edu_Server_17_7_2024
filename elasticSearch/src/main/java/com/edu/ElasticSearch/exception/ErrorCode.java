package com.edu.ElasticSearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_NEW_STUDENT(1008,"Have a problem in the process", INTERNAL_SERVER_ERROR),
    ERROR_SEND_EMAIL(1009,"Cannot send this email", INTERNAL_SERVER_ERROR),
    ERR_CREATE_NEW_COURSE(1010,"Can  not create a new course",INTERNAL_SERVER_ERROR),
    ERR_CREATE_TEACHER(1011,"YOU CAN NOT CREATE A NEW TEACHER",INTERNAL_SERVER_ERROR),
    ERR_MODIFY_INFOR_COURSE(1012,"change information of this course failure",INTERNAL_SERVER_ERROR),
    ERR_ADD_VIDEO(1013,"create a new video failure",INTERNAL_SERVER_ERROR),
    ERR_REMOVE_VIDEDO(1014,"remove video failure", INVALID_KEY.statusCode),
    ERR_SAME_NAME_VIDEO(1015,"video name exist",INTERNAL_SERVER_ERROR),
    ERROR_SUBJECT_CODE_EXIST(5001,"code existed",HttpStatus.BAD_REQUEST),
    ERROR_SUBJECT_NAME_EXIST(5002,"name existed",HttpStatus.BAD_REQUEST),
            ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
