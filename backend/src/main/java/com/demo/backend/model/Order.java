package com.demo.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders") 
public class Order {
	public Order(Long id, String item, Long amount, Long customerId) {
		super();
		this.id = id;
		this.item = item;
		this.amount = amount;
		this.customerId = customerId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String item;
	private Long amount; // for real system must be BigDecimal
	private Long customerId;

	protected Order() {
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", item=" + item + ", amount=" + amount + ", customerId=" + customerId + "]";
	}

	public Long getId() {
		return id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
