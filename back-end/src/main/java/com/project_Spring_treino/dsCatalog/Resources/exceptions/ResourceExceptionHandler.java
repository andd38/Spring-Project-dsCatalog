package com.project_Spring_treino.dsCatalog.Resources.exceptions;

import com.project_Spring_treino.dsCatalog.Services.StandardError;
import com.project_Spring_treino.dsCatalog.Services.exception.ExceptionIdNotFound;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ExceptionIdNotFound.class)
    public ResponseEntity<StandardError> entityNotFound (ExceptionIdNotFound e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resources not status");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
