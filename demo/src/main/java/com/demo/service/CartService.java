package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.Cart;

public interface CartService {

	public Cart saveCart(Cart cart);
	public Optional<Cart> findByProduct(Integer productId);
	List<Cart> findAll();
	public void deleteCartItem(Integer id);
}
