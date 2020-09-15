package com.billing.spring.com.billing.repositories;

import com.billing.spring.com.billing.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

}
