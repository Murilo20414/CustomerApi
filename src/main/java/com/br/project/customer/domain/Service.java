package com.br.project.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Customer customer;

    private BigDecimal value;

}
