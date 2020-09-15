package com.billing.spring.com.billing.controllers;

import com.billing.spring.com.billing.entities.Customers;
import com.billing.spring.com.billing.entities.Products;
import com.billing.spring.com.billing.repositories.CustomersRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    CustomersRepository customersRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    // GET METHOD to get all the Customers
    @CrossOrigin
    @RequestMapping(value = "/allCustomers", method = RequestMethod.GET)
    public List<Customers> getAllCustomers() {
        return (List<Customers>) customersRepository.findAll();
    }

    // POST METHOD to create a new Customer
    @CrossOrigin
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customers customer) {
        String result = "";
        try {
            customersRepository.save(customer);
            result = "{ \"status\": 200, \"statusCode\": 1}";
        } catch (Exception e) {
            e.printStackTrace();
            result = "{ \"status\": 500, \"statusCode\": 2}";
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
