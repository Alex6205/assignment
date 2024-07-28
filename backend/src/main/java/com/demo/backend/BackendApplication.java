package com.demo.backend;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.backend.model.Customer;
import com.demo.backend.model.CustomerRepository;
import com.demo.backend.model.Order;
import com.demo.backend.model.OrderRepository;

@SpringBootApplication
public class BackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepo, OrderRepository orderRepo) {
		return (args) -> {
			// save a few customers
			Customer cust1 = new Customer("John", "Doe");
			customerRepo.save(cust1);
			Order order1 = new Order("Keyboard", 400l, cust1);
			orderRepo.save(order1);

			Customer cust2 = new Customer("Robert", "Luna");
			customerRepo.save(cust2);
			Order order2 = new Order("Mousepad", 250l, cust2);
			orderRepo.save(order2);

			Customer cust3 = new Customer("David", "Robinson");
			customerRepo.save(cust3);
			Order order3 = new Order("Monitor", 1200l, cust3);
			orderRepo.save(order3);

			Customer cust4 = new Customer("John", "Reinhardt");
			customerRepo.save(cust4);
			Order order4 = new Order("Mouse", 300l, cust4);
			orderRepo.save(order4);
			Order order5 = new Order("Keyboard", 400l, cust4);
			orderRepo.save(order5);

			Customer cust5 = new Customer("Betty", "Doe");
			customerRepo.save(cust5);

//			// fetch all orders
//			log.info("Orders found with findAll():");
//			log.info("-------------------------------");
//			orderRepo.findAll().forEach(order -> {
//				log.info(order.toString());
//			});
//			log.info("");
//			
//			List<Customer> custList = customerRepo.findByLastName(null);
//			orderRepo.findByCustomer(cust1, null);
			Customer customerDb = customerRepo.findById(1l);
			Set<Order> ordersSet = (orderRepo.findByCustomerId(1l));

			// fetch an individual customer by ID

			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customerDb.toString());
			log.info(ordersSet.toString());
			log.info("");
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			Iterable<Customer> custList = customerRepo.findAll();
//			custList.forEach(customer -> {
//				customer.setOrders(orderRepo.findByCustomer(customer, null));
//			});
//			log.info("-------------------------------");
//			custList.forEach(customer -> {
//				log.info(customer.toString());
//			});
//			log.info("");

//			// fetch an individual customer by ID
//			Customer customer = customerRepo.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Luna'):");
//			log.info("--------------------------------------------");
//			customerRepo.findByLastName("Luna").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			log.info("");
		};
	}
}
