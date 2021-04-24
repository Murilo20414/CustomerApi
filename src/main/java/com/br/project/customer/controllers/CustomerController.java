package com.br.project.customer.controllers;

import com.br.project.customer.domain.Customer;
import com.br.project.customer.repository.CustomerRepository;
import dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.xml.ws.ResponseWrapper;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerController) {
        this.customerRepository = customerController;
    }

    @PostMapping("customer/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerDTO.convertToEntity();
        return customerRepository.save(customer);
    }

    @GetMapping("customer/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer deleteById(@PathVariable Long id){
        return customerRepository
                .findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return customer;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateById(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        return customerRepository
                .findById(id)
                .map(customer -> {
                    customer.setName(customerDTO.getName());
                    customer.setCpf(customerDTO.getCpf());
                    return customerRepository.save(customer);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

}
