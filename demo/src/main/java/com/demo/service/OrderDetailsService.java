package com.demo.service;

import java.util.List;

import com.demo.model.OrderDetails;

public interface OrderDetailsService {

	List<OrderDetails> findAll();
	
	OrderDetails saveOrderDetails(OrderDetails details);
}
