package com.example.product.controllerAdvice;

import com.example.product.dtos.ErrorResponseDto;
import com.example.product.services.InvalidProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidProduct(){
        return new ResponseEntity<>(new ErrorResponseDto("Invalid product id"), HttpStatus.NOT_FOUND);
    }
}