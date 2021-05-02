package com.br.project.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ServiceProvided {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Customer customer;

    @Column
    private BigDecimal value;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateService;



}
