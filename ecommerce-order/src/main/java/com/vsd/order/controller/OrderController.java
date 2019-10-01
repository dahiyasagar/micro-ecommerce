package com.vsd.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsd.order.constants.OrderConstants;
import com.vsd.order.dto.OrderProductDto;
import com.vsd.order.model.ShoppingOrder;
import com.vsd.order.model.OrderItem;
import com.vsd.order.service.OrderService;

@RestController
@RequestMapping(OrderConstants.API_PATH)
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShoppingOrder> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
        	orderItems.add(new OrderItem(dto.getProduct().getName(), dto.getProduct().getPrice(), 
        			dto.getQuantity()));
        }
        ShoppingOrder order = new ShoppingOrder(orderItems);
        order = this.orderService.create(order);

		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	public static class OrderForm {

		private List<OrderProductDto> productOrders;

		public List<OrderProductDto> getProductOrders() {
			return productOrders;
		}

		public void setProductOrders(List<OrderProductDto> productOrders) {
			this.productOrders = productOrders;
		}
	}

}
