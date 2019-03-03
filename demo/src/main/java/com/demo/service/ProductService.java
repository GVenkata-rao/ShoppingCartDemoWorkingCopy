package com.demo.service;

import java.util.List;

import com.demo.model.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(Integer id);

}
