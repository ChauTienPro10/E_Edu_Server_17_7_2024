package edu.member.student.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum StudentErr {
    PHONE_INVALID(3001, "phone invalid",HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(3002, "email invalid",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(3003,"password unsecure",HttpStatus.BAD_REQUEST)

    ;

    StudentErr(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;


}
