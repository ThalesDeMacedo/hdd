package br.com.sw.hdd.application.controller;

import br.com.sw.hdd.infractructure.exception.IntegrationException;
import br.com.sw.hdd.infractructure.exception.PlanetNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity unknownError(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(PlanetNotFoundException.class)
    public ResponseEntity planetNotFoundException(PlanetNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity duplicateKeyException(DuplicateKeyException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity integrationException(IntegrationException e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

}
