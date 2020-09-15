package com.billing.spring.com.billing.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.billing.spring.com.billing.entities.Products;
import com.billing.spring.com.billing.repositories.ProductsRepository;

@RestController
public class ProductsController {

	@Autowired
	ProductsRepository productsRepository;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	// GET Method to get all the Products
	@CrossOrigin
	@RequestMapping(value = "/allProducts", method = RequestMethod.GET)
	public List<Products> getAllProducts() {
		return (List<Products>) productsRepository.findAll();
	}

	// POST Method to create a new Product
	@CrossOrigin
	@PostMapping(value = "/createProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProduct(@RequestBody Products products) throws IOException {
		String result =  "";
		try{
			productsRepository.save(products);
			result = "{ \"status\": 200, \"statusCode\": 1}";
		} catch (Exception e) {
			result = "{ \"status\": 404, \"statusCode\": 2}";
			e.printStackTrace();
		}
		/* STATUS CODES
		*  1 - Successful Insert
		*  2 - Error while inserting
		*  */
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
