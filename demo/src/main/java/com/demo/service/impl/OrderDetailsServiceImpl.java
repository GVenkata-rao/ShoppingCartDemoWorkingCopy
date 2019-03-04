package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.OrderDetailsRepository;
import com.demo.model.OrderDetails;
import com.demo.service.OrderDetailsService;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsRepository orderDetailsRepo;
	
	@Override
	public List<OrderDetails> findAll() {
		return orderDetailsRepo.findAll();
	}

	@Override
	public OrderDetails saveOrderDetails(OrderDetails details) {
		// TODO Auto-generated method stub
		return orderDetailsRepo.save(details);
	}
	
	

}
