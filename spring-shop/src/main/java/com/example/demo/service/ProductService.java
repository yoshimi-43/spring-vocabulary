package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	private ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

}
