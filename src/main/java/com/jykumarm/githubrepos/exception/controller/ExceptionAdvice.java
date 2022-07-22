package com.jykumarm.githubrepos.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler
    public void handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Bad Request");
    }
}
