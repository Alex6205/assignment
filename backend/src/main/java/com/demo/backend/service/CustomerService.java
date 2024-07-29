package com.demo.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.backend.model.Customer;
import com.demo.backend.model.CustomerRepository;

@Service
public class CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepo;

	public Customer addCustomer(Customer customer) {
		// check if customer in DB
		log.info("------POST add customer-----------------");
		log.info(customer.toString());
		log.info("");
		// TODO validation ??????
		List<Customer> listCustomers = customerRepo.findByLastNameAndFirstName(customer.getLastName(),
				customer.getFirstName());
		if (listCustomers.isEmpty()) {
			customer = customerRepo.save(customer);
			return customer;
		}
		return new Customer(); // if customer exist - empty object
	}

	public List<Customer> findAllCustomers() {
		List<Customer> customerList = new ArrayList<>();
		customerRepo.findAll().forEach(customerList::add);
		log.info("--------GET all customers-------------------");
		customerList.forEach(customer -> {
			log.info(customer.toString());
		});
		log.info("");
		return customerList;
	}

	public Optional<Customer> findCustomerById(Long id) {
		Optional<Customer> customer = customerRepo.findById(id);
		log.info("------GET by Id of customer-----------------");
		log.info(customer.toString());
		log.info("");
		return customer;
	}
}
