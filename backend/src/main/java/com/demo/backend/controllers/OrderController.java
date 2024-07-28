package com.demo.backend.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.model.Order;
import com.demo.backend.service.OrderService;

@RestController
@RequestMapping("/restapi/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "customer/{id}")
	public Set<Order> findById(@PathVariable Long id) {
		return orderService.findOrdersByCustomer(id);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Order addOrderr(@RequestBody Order order) {
		Order orderDb = orderService.addOrder(order);
		if (orderDb.getId() != null) {
			order = orderDb;
		}
		return order;
	}

}
