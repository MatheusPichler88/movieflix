package com.ufn.movieflix.config;

import com.ufn.movieflix.exception.EmailJaCadastradoException;
import com.ufn.movieflix.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundExceptions(UsernameOrPasswordInvalidException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEmailJaCadastradoException(EmailJaCadastradoException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errors;
    }
}
