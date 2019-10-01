package com.vsd.order.service;

import java.time.LocalDate;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsd.order.model.ShoppingOrder;
import com.vsd.order.repository.OrderRepository;

@Service
@Transactional
@EnableBinding(Source.class)
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
    private Source source;

	public OrderServiceImpl(OrderRepository productRepository, Source source) {
		this.orderRepository = productRepository;
		this.source = source;
	}

	@Override
	@Transactional(readOnly = true)
    public Iterable<ShoppingOrder> getAllOrders() {
        return this.orderRepository.findAll();
    }
     
    @Override
    public ShoppingOrder create(ShoppingOrder order) {
        order.setDateCreated(LocalDate.now());
        ShoppingOrder createdOrder = this.orderRepository.save(order);
        source.output().send(MessageBuilder.withPayload(order).build());
        return createdOrder;
    }
    
}
