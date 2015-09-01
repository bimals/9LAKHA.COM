package com.jewelhaat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bimalsahay.service.UserService;
import com.jewelhaat.db.service.ProductService;

@Controller
public class SellerController {
	
	@Autowired ProductService productService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/pricing", method = RequestMethod.GET)
	public String writeProduct() {
		
		return "pricing";
	}
	
	@RequestMapping(value="/seller", method = RequestMethod.GET)
	public String seller() {
		
		return "seller";
	}
	
	@RequestMapping(value="/seller/new", method = RequestMethod.GET)
	public String createSeller() {
		
		return "seller";
	}
}
