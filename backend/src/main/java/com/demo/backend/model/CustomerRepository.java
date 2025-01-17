package com.demo.backend.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastNameAndFirstName(String lastName, String firstName);

	Customer findById(long id);

}