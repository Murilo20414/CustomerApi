package com.br.project.customer.controllers;

import com.br.project.customer.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex) {

        BindingResult bindingResult =  ex.getBindingResult();

        List<String> messeges = bindingResult.getAllErrors().
                    stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList()); //pegando todos os erros, tranformando-os em string e colocando tudo em uma lista

        return new ApiErrors(messeges);

    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {

        String messege = ex.getMessage();
        HttpStatus statusCode = ex.getStatus();

        ApiErrors apiErrors = new ApiErrors(messege);

        return new ResponseEntity(apiErrors, statusCode);
    }

}
