package com.ejb.demo.backend;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Outputs are copied because of the enhancement of OpenJPA.
 */
@Singleton
@Lock(LockType.WRITE)
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
	@PersistenceContext
	private EntityManager em;

	@POST
	@Path("/restapi/orders")
	public Response addOrder(Order order) {
		em.persist(order);
		return Response.ok().header("Access-Control-Allow-Origin", "http://localhost:4200")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "POST").entity(order).build();
	}

	@Path("/restapi/orders/customer/{id}")
	@GET
	public Response findOrdersByCustomer(@PathParam(value = "id") long customerId) {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = em.createNamedQuery("user.findbyid", Customer.class).setParameter(1, customerId)
				.setFirstResult(0).setMaxResults(100).getResultList();
		if (customers.size() == 1) {
			orders = findAllOrdersByCustIdDb(customers.get(0));
		}
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(orders).build();
	}

	@Path("/restapi/orders")
	@GET
	public Response findAllOrders() {
		List<Order> found = findAllOrdersDb();
		return Response.ok(found).header("Access-Control-Allow-Origin", "*").entity(found).build();
	}

	public List<Order> findAllOrdersDb() {
		List<Order> orders = new ArrayList<>();
		List<Order> found = em.createNamedQuery("order.list", Order.class).setFirstResult(0).setMaxResults(100)
				.getResultList();
		for (Order u : found) {
			orders.add(u.copy());
		}
		return orders;
	}

	public List<Order> findAllOrdersByCustIdDb(Customer customer) {
		List<Order> orders = new ArrayList<>();
		List<Order> found = em.createNamedQuery("order.findbyid", Order.class).setParameter("customer", customer)
				.setFirstResult(0).setMaxResults(100).getResultList();
		for (Order u : found) {
			orders.add(u.copy());
		}
		return orders;
	}

	public Order create(Order order) {
		em.persist(order);
		return order.copy();
	}
}
