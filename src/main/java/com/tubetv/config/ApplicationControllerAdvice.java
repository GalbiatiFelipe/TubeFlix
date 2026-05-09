package com.tubetv.config;

import com.tubetv.exception.UsernameOrPasswordInvalidException;
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

    // Se ocorrer uma excetpion deste tipo
    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // a resposta será este status
    public String handleNotFoundException(UsernameOrPasswordInvalidException ex){
        return ex.getMessage(); // e também a mensagem que foi passada no catch
    }


    // *map é uma lista com dois campos por elemento
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
            errors.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return errors;
        /*
        * Quando mais de um atributo não for validado ele retorna uma lista, e vai pode retornar varias mensagens ao mesmo tempo
        * */
    }

}
