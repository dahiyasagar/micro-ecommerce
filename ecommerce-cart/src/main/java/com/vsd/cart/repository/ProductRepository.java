package com.vsd.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsd.cart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

//	@Query("select p from Product p")
//	public Stream<Product> findAllAndStream();

}
