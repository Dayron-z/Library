package com.library.library.api.error_handler;

import com.library.library.api.dto.errors.ErrorResponse;
import com.library.library.utils.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(BadRequestException badRequestException){
                                                        //int -> status                 //Message
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), badRequestException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
