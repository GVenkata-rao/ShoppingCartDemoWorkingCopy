package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.service.ProductService;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	/*@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		ProductModel productModel = new ProductModel();
		modelMap.put("products", productModel.findAll());
		return "product/index";
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
	public String productList(ModelMap modelMap){
		modelMap.put("products", productService.findAll());
		return "product/index";
		
	}

}
