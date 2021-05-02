package com.br.project.customer.repository;

import com.br.project.customer.domain.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long> {


    @Query("select s from ServiceProvided s join s.customer c " +
            "where upper( c.name ) like upper( :name ) and month( s.dateService ) = :month")
    List<ServiceProvided> findByNameCustomerAndMonth(@Param("name") String name,@Param("month") Integer month);
}
