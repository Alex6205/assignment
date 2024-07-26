package com.demo.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.backend.model.Customer;
import com.demo.backend.model.CustomerRepository;

@SpringBootApplication
public class BackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepo) {
		return (args) -> {
			// save a few customers
			customerRepo.save(new Customer("John", "Doe"));
			customerRepo.save(new Customer("Robert", "Luna"));
			customerRepo.save(new Customer("David", "Robinson"));
			customerRepo.save(new Customer("John", "Reinhardt"));
			customerRepo.save(new Customer("Betty", "Doe"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			customerRepo.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = customerRepo.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Luna'):");
			log.info("--------------------------------------------");
			customerRepo.findByLastName("Luna").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}
}
