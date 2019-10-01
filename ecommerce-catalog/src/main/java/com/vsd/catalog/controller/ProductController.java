package com.vsd.catalog.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsd.catalog.constants.ProductConstants;
import com.vsd.catalog.model.Product;
import com.vsd.catalog.service.ProductService;

@RestController
@RequestMapping(ProductConstants.API_PATH)
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> findAll() {
		List<Product> emps = productService.findAll();
		return emps;
	}

}
