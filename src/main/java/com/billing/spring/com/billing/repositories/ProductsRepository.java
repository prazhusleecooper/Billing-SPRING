package com.billing.spring.com.billing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.billing.spring.com.billing.entities.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
