package com.vsd.order.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.vsd.order.model.ShoppingOrder;

public interface OrderService {

	public Iterable<ShoppingOrder> getAllOrders();

	public ShoppingOrder create(@NotNull(message = "The order cannot be null.") @Valid ShoppingOrder order);

}
