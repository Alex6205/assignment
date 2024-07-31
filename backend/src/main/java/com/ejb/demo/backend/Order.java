package com.ejb.demo.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Orders")
@NamedQuery(name = "order.list", query = "select u from Order u")
@NamedQueries(value = { @NamedQuery(name = "order.findbyid", query = "select u from Order u where u.customer_id=?1") })
@XmlRootElement(name = "order")
public class Order {
	@Id
	@GeneratedValue
	private long id;
	private String item;
	private long amount;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	public Order(String item, long amount) {
		super();
		this.item = item;
		this.amount = amount;
	}

	public Order(String item, long amount, Customer customer) {
		super();
		this.item = item;
		this.amount = amount;
		this.customer = customer;
	}

	public Order() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order copy() {
		Order order = new Order();
		order.setId(getId());
		order.setItem(getItem());
		order.setAmount(getAmount());
		order.setCustomer(getCustomer());
		return order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !Order.class.isAssignableFrom(o.getClass())) {
			return false;
		}

		Order order = (Order) o;

		return id == order.id;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}
}
