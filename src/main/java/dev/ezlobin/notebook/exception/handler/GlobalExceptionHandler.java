package dev.ezlobin.notebook.exception.handler;

import dev.ezlobin.notebook.exception.WrongRequestDataException;
import dev.ezlobin.notebook.exception.NotebookMapperException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotebookMapperException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(NotebookMapperException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    @ExceptionHandler(WrongRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(WrongRequestDataException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
