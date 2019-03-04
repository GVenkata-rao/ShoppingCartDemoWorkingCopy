package com.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.dto.InvoiceData;
import com.demo.model.Cart;
import com.demo.model.OrderDetails;
import com.demo.model.Orders;
import com.demo.service.CartService;
import com.demo.service.OrderDetailsService;
import com.demo.service.OrderService;
import com.demo.util.GeneratePdfReport;

@Controller
public class OrderController {

	@Autowired
	CartService CartService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailsService orderDetailsService;

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
				orderDetailsService.saveOrderDetails(new OrderDetails(orders, cart.getProduct(), cart.getQuanity(), cart.getProduct().getPrice(), cart.getQuanity() * cart.getProduct().getPrice()));
			}
		}
		return "redirect:/pdfreport";
	}
	
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

       // List<City> cities = (List<City>) cityService.findAll();
		
		List<OrderDetails> details=orderDetailsService.findAll();
		
		List<InvoiceData> invoiceDatas=new ArrayList<>();
		for( OrderDetails od:details) {
			invoiceDatas.add(new InvoiceData(od.getProduct().getName(), od.getProduct().getPrice(), od.getQuanity(), od.getAmount()));
		}

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(invoiceDatas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
