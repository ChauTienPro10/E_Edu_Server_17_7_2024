package com.edu.ElasticSearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum TeacherException {
    TEACHER_CREATE_LEVEL_INVALID(4001, "level invalid", HttpStatus.BAD_REQUEST),
    TEACHER_CREATE_SUBJECT_CODE_INVALID(4002, "subject code invalid", HttpStatus.BAD_REQUEST),
    TEACHER_CREATE_EMAIL_EXISTED(4003,"Email was used",HttpStatus.BAD_REQUEST),
    TEACHER_EMAIL_INVALID(4002,"email invalid",HttpStatus.BAD_REQUEST)
    ;

    TeacherException(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
