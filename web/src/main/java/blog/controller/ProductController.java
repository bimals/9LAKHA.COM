package blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.AccountUser;
import com.bimalsahay.service.UserService;

import blog.db.service.ProductService;
import blog.model.Product;

@Controller
public class ProductController {
	
	@Autowired ProductService productService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String writeBlog() {
		
		return "home";
	}
	
	@RequestMapping(value="/user/createproduct", method = RequestMethod.POST)
	@ResponseBody
	public Product createProduct(@RequestBody Product product) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		product.setUserId(user.getId());
		Product newProduct = productService.createProduct(product);
		//user.getProductId().add(newProduct.getId());
		//userService.updateUser(user);
		return product;
	}

	@RequestMapping(value="/user/addproductimage", method = RequestMethod.POST)
	@ResponseBody
	public Product addProductImage(@RequestParam(value = "productId", required = false) String productId, @RequestParam(value = "fileToUpload", required = false) CommonsMultipartFile fileToUpload, 
			MultipartHttpServletRequest mrequest) throws IOException {
		
		Product product = productService.findById(productId);
		productService.addProductImage(product, fileToUpload);

		return product;
	}
	
}
