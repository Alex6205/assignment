package com.demo.backend.service;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.backend.model.Customer;
import com.demo.backend.model.Order;
import com.demo.backend.model.OrderRepository;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	CustomerService customerService;

	public Set<Order> findOrdersByCustomer(Long customerId) {
		Set<Order> orderList = orderRepo.findByCustomerId(customerId);
		log.info("------GET orders by customer-----------------");
		orderList.forEach(order -> {
			log.info(order.toString());
		});
		log.info("");
		return orderList;
	}

	public Order addOrder(Order order) {
		// TODO validation
		Optional<Customer> customer = customerService.findCustomerById(order.getCustomer().getId());
		// validate Optional
		Order orderNew = new Order(order.getItem(), order.getAmount(), customer.get());
		orderRepo.save(orderNew);
		log.info("------POST add order-----------------");
		log.info(orderNew.toString());
		log.info("");
		return orderNew;
	}

}
