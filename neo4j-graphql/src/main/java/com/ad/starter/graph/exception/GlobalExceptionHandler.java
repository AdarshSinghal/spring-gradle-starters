package com.ad.starter.graph.exception;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.exceptions.ServiceUnavailableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    public void handleServiceUnavailable(ServiceUnavailableException e) {
        log.info(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleGeneric(Exception e) {
        e.printStackTrace(); //During initial phase, it's essential to have complete stack trace in console, later we can replace with logs.
    }

}
