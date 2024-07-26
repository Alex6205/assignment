package com.demo.backend.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByCustomerId(Long customerId);

	List<Order> findByItem(String item);

	List<Order> findByAmount(Long amount);

	Order findById(long id);
}
