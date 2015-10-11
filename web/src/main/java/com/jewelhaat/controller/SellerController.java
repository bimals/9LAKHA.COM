package com.jewelhaat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimalsahay.model.AccountUser;
import com.bimalsahay.service.PaymentService;
import com.bimalsahay.service.UserService;
import com.jewelhaat.db.service.CompanyService;
import com.jewelhaat.db.service.ProductService;
import com.jewelhaat.db.service.SellerService;
import com.jewelhaat.model.Company;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.paypal.api.payments.CreditCard;

@Controller
public class SellerController {
	
	@Autowired ProductService productService;
	@Autowired UserService userService;
	@Autowired SellerService sellerService;
	@Autowired CompanyService companyService;
	@Autowired PaymentService paymentService;
	
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
	
	@RequestMapping(value="/check/{field}", method = RequestMethod.POST)
	@ResponseBody
	public CheckResponse checkField(@PathVariable("field") String field) {
		CheckResponse res = new CheckResponse();
		res.setUnique(false);
		return res;
	}
	
	
	@RequestMapping(value="/seller/create", method = RequestMethod.POST)
	@ResponseBody
	public Company createSeller(@RequestBody Company company) throws Exception {
		CreditCard creditCard = new CreditCard(); 
		creditCard.setExpireMonth(11); 
		creditCard.setExpireYear(2018); 
		creditCard.setNumber("4417119669820331"); 
		creditCard.setType("visa");
		
		CreditCard response = paymentService.saveCreditCardInfo(creditCard);
		BasicDBObject res = new BasicDBObject();
		DBObject dbObject = (DBObject) JSON.parse(response.toJSON());
		company.getCreditCards().add(dbObject);
		
		AccountUser user = userService.getLoggedInUser();
		
		company.setUserId(user.getId());
		company.setExpireYear(null);
		company.setCvv(null);
		company.setCardNumber(null); 
		company.setCardHolderName(null);
		
		company = companyService.createCompany(company);

		return company;
		
	}
	
	
	
	public class CheckResponse {
		boolean isUnique;

		public boolean isUnique() {
			return isUnique;
		}

		public void setUnique(boolean isUnique) {
			this.isUnique = isUnique;
		}
	}
}
