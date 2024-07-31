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
		return Response.ok(order.copy()).build();
	}

	@Path("/restapi/orders")
	@GET
	public List<Order> findOrdersByCustomer(long customerId) {
		List<Order> orders = new ArrayList<>();
		List<Order> found = em.createNamedQuery("order.findbyid", Order.class).setParameter("id", customerId)
				.setMaxResults(100).getResultList();
		for (Order u : found) {
			orders.add(u.copy());
		}
		return orders;
	}

	@Path("/restapi/orders")
	@GET
	public List<Order> findAllOrders() {
		List<Order> orders = new ArrayList<>();
		List<Order> found = em.createNamedQuery("order.list", Order.class).setFirstResult(0).setMaxResults(100)
				.getResultList();
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
