package com.billing.spring.com.billing.repositories;

import com.billing.spring.com.billing.entities.Products;
import com.billing.spring.com.billing.entities.Purchase_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Purchase_DetailsRepository extends JpaRepository<Purchase_details, Integer> {

}
