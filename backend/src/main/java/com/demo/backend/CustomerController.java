package com.demo.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.model.Customer;
import com.demo.backend.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/testing/customers")
	public List<Customer> findAll() {
		return customerService.findAllCustomers();
	}

	@GetMapping(value = "/testing/findby/{id}")
	public Optional<Customer> findById(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}

	@PostMapping(value = "/testing/addCustomer", consumes = "application/json", produces = "application/json")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer customerDb = customerService.addCustomer(customer);
		if (customerDb.getId() != 0) {
			customer = customerDb;
		}
		return customer;
	}

}
