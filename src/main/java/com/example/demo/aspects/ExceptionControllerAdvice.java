package com.example.demo.aspects;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;


@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Problem onUnknownException(Exception e) {
        log.error(e.toString(), e);
        return Problem.builder()
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withTitle("UNKNOWN_EXCEPTION")
                .withDetail(e.getMessage())
                .build();
    }

}