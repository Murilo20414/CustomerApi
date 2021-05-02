package com.br.project.customer.controllers;

import com.br.project.customer.domain.Customer;
import com.br.project.customer.repository.CustomerRepository;
import com.br.project.customer.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerDTO.convertToEntity();
        return customerRepository.save(customer);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("/{id}")
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

    @PutMapping("/{id}")
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
