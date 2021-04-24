package com.br.project.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    public Customer(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;

    @PrePersist
    private void prePersist(){
        setRegistrationDate(LocalDate.now());
    }
}
