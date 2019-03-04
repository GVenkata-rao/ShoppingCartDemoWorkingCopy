package com.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.Cart;
import com.demo.model.Item;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.service.CartService;
import com.demo.service.ProductService;

@Controller
@RequestMapping(value = "cart")
public class CartController {

	
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("cart", cartService.findAll());
		//System.out.println(cart.toString());
		return "cart/index";
	}

	@RequestMapping(value="buy/{id}",method=RequestMethod.GET)
	public String addCart(@PathVariable("id") Integer  id,HttpSession httpSession){
	 Product product=productService.findById(id);
	 Optional<Cart> cart=cartService.findByProduct(product.getId());
	 if(cart.isPresent()){
		 cart.get().setQuanity(cart.get().getQuanity() + 1);
		 cartService.saveCart(cart.get());
	 }else{
		 User user=(User)httpSession.getAttribute("user");
		 Cart cart2=new Cart();
		 cart2.setProduct(product);
		 cart2.setQuanity(1);
		 cart2.setUser(user);
		 cartService.saveCart(cart2);
	 }
	 
		return "redirect:/cart/index";
	}
	
	
	
	/*@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") String id, HttpSession session) {
		ProductModel productModel = new ProductModel();
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(productModel.find(id), 1));
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(id, cart);
			if (index == -1) {
				cart.add(new Item(productModel.find(id), 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
	}*/

	
	@RequestMapping(value="remove/{id}",method=RequestMethod.GET)
	public String removeItemFrmCart(@PathVariable("id") Integer id){
		
		
		cartService.deleteCartItem(id);
		return "redirect:/cart/index";
	}
	/*@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") String id, HttpSession session) {
		ProductModel productModel = new ProductModel();
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(id, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index";
	}*/

	private int exists(String id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getCode().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}

	@RequestMapping(value="/")
	public String checkOutOrder(){
		return " ";
	}
	
	
}
