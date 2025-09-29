package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Product;
import com.example.demo.repository.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner{
	
	private final ProductRepository productRepository;
	
	private DataLoader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (productRepository.count() == 0) {
			productRepository.save(new Product("商品1", 1000));
			productRepository.save(new Product("商品2", 1200));
			productRepository.save(new Product("商品3", 1500));
		}
	}

}
