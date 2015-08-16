package blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.db.service.ProductService;
import blog.model.Product;
import blog.model.SearchCriteria;

@Controller
public class SearchController {
	
	@Autowired ProductService productService;
	
	@RequestMapping(value="product/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> searchProduct(@RequestBody SearchCriteria searchCriteria) {
		List<Product> products = productService.searchProductByKeyWord(searchCriteria.getSearchText());
		
		return products;
	}
}
