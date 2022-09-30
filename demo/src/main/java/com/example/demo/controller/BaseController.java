package com.example.demo.controller;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    public BaseController() {
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(Exception catchedException) {
        return new ResponseEntity<>(new ErrorDTO(catchedException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
