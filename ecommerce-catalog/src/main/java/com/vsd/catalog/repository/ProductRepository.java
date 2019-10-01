package com.vsd.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsd.catalog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

//	@Query("select p from Product p")
//	public Stream<Product> findAllAndStream();

}
