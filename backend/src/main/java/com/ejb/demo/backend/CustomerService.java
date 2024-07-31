/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Outputs are copied because of the enhancement of OpenJPA.
 */
@Singleton
@Lock(LockType.WRITE)
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)

public class CustomerService {

	@PersistenceContext
	private EntityManager em;

	@POST
	@Path("/restapi/customers")
	public Response addCustomer(Customer customer) {
		if (customer.getId() != 0) {
			Customer customerDb = em.find(Customer.class, customer.getId());
			if (customerDb == null) {
				throw new IllegalArgumentException("user id " + customer.getId() + " not found");
			}
		}
		em.persist(customer);

		return Response.ok(customer.copy()).build();
	}
	
	@Path("/restapi/customers")
	@GET
	public List<Customer> findAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		List<Customer> found = em.createNamedQuery("user.list", Customer.class).setFirstResult(0).setMaxResults(100)
				.getResultList();
		for (Customer u : found) {
			customers.add(u.copy());
		}
		return customers;
	}
	@Path("/create")
	@PUT
	public Customer create(@QueryParam("firsName") String firsName, @QueryParam("lastName") String lastName) {
		Customer customer = new Customer();
		customer.setFirstName(firsName);
		customer.setLastName(lastName);
		em.persist(customer);
		return customer.copy();
	}

	@Path("/list")
	@GET
	public List<Customer> list(@QueryParam("first") @DefaultValue("0") int first,
			@QueryParam("max") @DefaultValue("20") int max) {
		List<Customer> customers = new ArrayList<Customer>();
		List<Customer> found = em.createNamedQuery("user.list", Customer.class).setFirstResult(first).setMaxResults(max)
				.getResultList();
		for (Customer u : found) {
			customers.add(u.copy());
		}
		return customers;
	}

	@Path("/show/{id}")
	@GET
	public Customer find(@PathParam("id") long id) {
		Customer customer = em.find(Customer.class, id);
		if (customer == null) {
			return null;
		}
		return customer.copy();

	}

	@Path("/delete/{id}")
	@DELETE
	public void delete(@PathParam("id") long id) {
		Customer customer = em.find(Customer.class, id);
		if (customer != null) {
			em.remove(customer);
		}
	}

	@Path("/update/{id}")
	@POST
	public Response update(@PathParam("id") long id, @QueryParam("firsName") String firsName,
			@QueryParam("lastName") String lastName) {
		Customer customer = em.find(Customer.class, id);
		if (customer == null) {
			throw new IllegalArgumentException("user id " + id + " not found");
		}

		customer.setFirstName(firsName);
		customer.setLastName(lastName);
		em.merge(customer);

		return Response.ok(customer.copy()).build();
	}
}
