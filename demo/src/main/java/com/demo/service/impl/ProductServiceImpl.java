package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProductRepository;
import com.demo.model.Product;
import com.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	public List<Product> findAll(){
		 return productRepo.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepo.findOne(id);
	}
	
	
}
