package com.vsd.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsd.order.model.ShoppingOrder;

public interface OrderRepository extends JpaRepository<ShoppingOrder, Long> {

//	@Query("select p from Product p")
//	public Stream<Product> findAllAndStream();

}
