package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.User;
import com.demo.service.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAuth(HttpServletRequest request, HttpSession httpSession) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.findByUsernameAndPassword(username, password);
		if (user != null) {
			httpSession.setAttribute("user", user);
			return "redirect:/product";
		}

		return "login";
	}

}
