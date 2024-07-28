package com.demo.backend.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.backend.model.Customer;
import com.demo.backend.model.Order;
import com.demo.backend.model.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	CustomerService customerService;

	public Set<Order> findOrdersByCustomer(Long customerId) {
		return orderRepo.findByCustomerId(customerId);
	}

	public Order addOrder(Order order) {
		// TODO validation
		Optional<Customer> customer = customerService.findCustomerById(order.getCustomer().getId());
		//validate Optional
		Order orderNew = new Order(order.getItem(), order.getAmount(), customer.get());
		orderRepo.save(orderNew);
		return orderNew;
	}

}
