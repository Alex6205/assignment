/*
 *      Licensed to the Apache Software Foundation (ASF) under one or more
 *     contributor license agreements.  See the NOTICE file distributed with
 *     this work for additional information regarding copyright ownership.
 *     The ASF licenses this file to You under the Apache License, Version 2.0
 *     (the "License"); you may not use this file except in compliance with
 *     the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.ejb.demo.backend;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.OpenEjbContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.ejb.embeddable.EJBContainer;

public class CustomerServiceTest {

	private static Context context;
	private static CustomerService customerService;
	private static OrderService orderService;
	private static List<Customer> customers = new ArrayList<>();
	private static List<Order> orders = new ArrayList<>();

	@BeforeClass
	public static void start() throws NamingException {
		Properties properties = new Properties();
		properties.setProperty(OpenEjbContainer.OPENEJB_EMBEDDED_REMOTABLE, "true");
		context = EJBContainer.createEJBContainer(properties).getContext();

		// create some records
		customerService = (CustomerService) context.lookup("java:global/backend/CustomerService");
		customers.add(customerService.create("John", "Doe"));
		customers.add(customerService.create("Robert", "Luna"));
		orderService = (OrderService) context.lookup("java:global/backend/OrderService");
		Customer customer = new Customer("", "");
		customer.setId(1);
		Order order = new Order("pad", 123, customer);
		orderService.create(order);
	}

	@AfterClass
	public static void close() throws NamingException {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void createOrder() {
		Customer customer = new Customer("", "");
		customer.setId(1);
		Order order = new Order("pad", 123, customer);
		WebClient.create("http://localhost:4204/backend").path("/order/restapi/orders").post(order);

		List<Order> list = orderService.findAllOrders(); // list(0, 100);
		for (Order u : list) {
			if (!orders.contains(u)) {
				customerService.delete(u.getId());
				return;
			}
		}
		fail("user was not added");
	}

//	@Test
	public void findOrdersByCustId() {
		List<Order> list = orderService.findOrdersByCustomer(1l);
		assertFalse(list.isEmpty());
	}
	@Test
	public void findAllOrders() {
		List<Order> list = orderService.findAllOrders();
		assertFalse(list.isEmpty());

	}

	@Test
	public void createCustomer() {
		Customer customer = new Customer("Betty", "Doe");
		WebClient.create("http://localhost:4204/backend").path("/user/restapi/customers").post(customer);

		List<Customer> list = customerService.findAllCustomers(); // list(0, 100);
		for (Customer u : list) {
			if (!customers.contains(u)) {
				customerService.delete(u.getId());
				return;
			}
		}
		fail("customer was not added");
	}

	@Test
	public void findAllCustomers() throws Exception {
		System.out.println("*******************************************************************");
//		Thread.sleep(100000);
		String users = WebClient.create("http://localhost:4204/backend").path("/user/restapi/customers")
				.get(String.class);
		assertFalse(users.isEmpty());
	}

}
