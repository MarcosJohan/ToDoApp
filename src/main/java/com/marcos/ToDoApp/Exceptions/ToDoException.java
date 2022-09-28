package com.marcos.ToDoApp.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToDoException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public ToDoException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
