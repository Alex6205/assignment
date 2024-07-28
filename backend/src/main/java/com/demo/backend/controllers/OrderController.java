package com.demo.backend.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.model.Order;
import com.demo.backend.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/testing/orderbycust/{id}")
	public Set<Order> findById(@PathVariable Long id) {
		return orderService.findOrdersByCustomer(id);
	}

	@PostMapping(value = "/testing/addorder", consumes = "application/json", produces = "application/json")
	public Order addOrderr(@RequestBody Order order, @RequestParam("customerId")Long customerId) {
		Order orderDb = orderService.addOrder(order, customerId);
		if (orderDb.getId() != null) {
			order = orderDb;
		}
		return order;
	}

}
