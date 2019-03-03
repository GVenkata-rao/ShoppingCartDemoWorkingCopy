package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.OrderDetailsRepository;
import com.demo.model.Cart;
import com.demo.model.OrderDetails;
import com.demo.model.Orders;
import com.demo.service.CartService;
import com.demo.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	CartService CartService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailsRepository orderDetailsRepo;

	@RequestMapping(value = "/checkOutOrders", method = RequestMethod.GET)
	public String checkOrder() {

		int amount = 0;
		List<Cart> cartList = CartService.findAll();
		if (cartList != null) {
			for (Cart cart : cartList) {
				amount += cart.getQuanity() * cart.getProduct().getPrice();

			}
		}
		Orders orders = orderService.saveOrder(new Orders(amount));

		if (cartList != null) {
			for (Cart cart : cartList) {
	
				orderDetailsRepo.save(new OrderDetails(orders, cart.getProduct(), cart.getQuanity(), cart.getProduct().getPrice(), cart.getQuanity() * cart.getProduct().getPrice()));
			}
		}
		return "Order Placed successfully !";
	}

}
