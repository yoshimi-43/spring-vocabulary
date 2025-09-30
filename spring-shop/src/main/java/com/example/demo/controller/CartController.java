package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Cart;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	private CartService cartService;
	
	private CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public List<Cart> getAllCart() {
		return cartService.getAllCart();
	}
	
	@PostMapping("/{productId}")
	public void addCartItem(@PathVariable Long productId) {
		cartService.addCartItem(productId);	
	}
	
	@PutMapping()
	public void updateCart(@RequestBody Cart cart) {
		cartService.updateCartItem(cart);
		
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable Long cartId) {
		cartService.deleteCart(cartId);
	}
	
	@PostMapping("/purchase")
	public void purchaseCartItem() {
		cartService.purchaseCart();
	}

}
