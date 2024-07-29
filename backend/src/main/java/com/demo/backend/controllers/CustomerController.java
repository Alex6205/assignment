package com.demo.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.model.Customer;
import com.demo.backend.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/restapi/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<Customer> findAll() {
		return customerService.findAllCustomers();
	}

	@GetMapping(value = "/{id}")
	public Optional<Customer> findById(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer customerDb = customerService.addCustomer(customer);
		if (customerDb.getId() != null) {
			customer = customerDb;
		}
		return customer;
	}

	@PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id) {
//TODO
	}

	@DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		// TODO
//        service.deleteById(id);
	}
}
