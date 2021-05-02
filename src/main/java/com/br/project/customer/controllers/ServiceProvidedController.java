package com.br.project.customer.controllers;

import com.br.project.customer.domain.Customer;
import com.br.project.customer.domain.ServiceProvided;
import com.br.project.customer.dto.ServiceProvidedDTO;
import com.br.project.customer.repository.CustomerRepository;
import com.br.project.customer.repository.ServiceProvidedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services-provided")
@RequiredArgsConstructor
public class ServiceProvidedController {

    private final ServiceProvidedRepository serviceProvidedRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceProvided save(@RequestBody @Valid ServiceProvidedDTO serviceDto) {
        ServiceProvided service =  serviceDto.convertToEntity();
        Customer customer = customerRepository
                                            .findById(serviceDto.getId_customer())
                                           .orElseThrow(() -> new ResponseStatusException(
                                                                    HttpStatus.BAD_REQUEST, "Cliente inexistente"));
        service.setCustomer(customer);

        return serviceProvidedRepository.save(service);
    }

    @GetMapping
    public List<ServiceProvided> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "month", required = false) Integer month
    ) {

        return serviceProvidedRepository.findByNameCustomerAndMonth("%"+name+"%", month);

    }

}
