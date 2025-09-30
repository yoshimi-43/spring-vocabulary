package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CartService {
	private CartRepository cartRepository;
	private ProductRepository productRepository;
	
	private CartService(CartRepository cartRepository, ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}
	
	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}
	
	public void addCartItem(Long productId) {
		Optional<Cart> cartItem = cartRepository.findByProductId(productId);
		//　カート内に同じIDの商品があれば数を追加する
		if(cartItem.isPresent()) {
			Cart item = cartItem.get();
			item.setQuantity(item.getQuantity() + 1);
			cartRepository.save(item);
		} else {
			//　カート内に同じIDの商品がなければ商品ごと追加する
			Product product = productRepository.findById(productId).orElseThrow(
					() -> new RuntimeException("商品情報がありません"));
			Cart newItem = new Cart(product, 1);
			cartRepository.save(newItem);
		}
	}
	
	public void updateCartItem(Cart cart) {
		Optional<Cart> cartItem = cartRepository.findById(cart.getId());
		if(cartItem.isPresent()) {
			Cart item = cartItem.get();
			item.setQuantity(cart.getQuantity());
			cartRepository.save(item);
		} else {
			 new RuntimeException("カート情報がありません");
		}
	}
	
	public void deleteCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}
}
