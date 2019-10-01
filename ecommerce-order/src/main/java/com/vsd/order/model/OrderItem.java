package com.vsd.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class OrderItem {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	@ManyToOne
    @JoinColumn
    private ShoppingOrder shoppingOrder;
	
	public OrderItem(@NotNull String name, @NotNull Double price, @NotNull Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	

}
