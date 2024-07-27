package com.demo.backend.model;

import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

	Set<Order> findByCustomer(Customer customer, Sort sort);

//	List<Order> findByCustomerId(Long customerId);
//
//	List<Order> findByItem(String item);
//
//	List<Order> findByAmount(Long amount);

//	Order findById(long id);
}
