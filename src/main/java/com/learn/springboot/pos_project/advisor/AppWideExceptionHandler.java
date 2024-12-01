package com.learn.springboot.pos_project.advisor;

import com.learn.springboot.pos_project.exception.NotFoundException;
import com.learn.springboot.pos_project.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    //default exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(Exception e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error Cought: "+e.getCause() , e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error Cought: "+e.getCause() , e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }


}
