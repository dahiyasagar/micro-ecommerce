package com.vsd.order.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "orderItems")
public class ShoppingOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateCreated;

	private Status status;

	@OneToMany(mappedBy = "shoppingOrder", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	public ShoppingOrder(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
		this.status = Status.PAID;
		this.dateCreated = LocalDate.now();
	}

	@Transient
	public Double getTotalOrderPrice() {
		double sum = 0D;
		for (OrderItem item : getOrderItems()) {
			sum += item.getPrice();
		}
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.getOrderItems().size();
	}

	public static enum Status {

		UNPAID,

		PAID,

		PREPARING,

		READY,

		DELIVERED;
	}
}
