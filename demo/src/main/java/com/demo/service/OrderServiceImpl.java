package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.OrderRepository;
import com.demo.model.Orders;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public Orders saveOrder(Orders order) {
		return orderRepo.save(order);
	}

}
