package com.br.project.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors {

    public ApiErrors(String messege) {
        this.errors = Arrays.asList(messege);
    }

    @Getter
    private List<String> errors;

}
