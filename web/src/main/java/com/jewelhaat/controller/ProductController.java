package com.jewelhaat.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.AccountUser;
import com.bimalsahay.service.UserService;
import com.jewelhaat.db.service.ProductService;
import com.jewelhaat.model.Comment;
import com.jewelhaat.model.Product;
import com.jewelhaat.model.ProductStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class ProductController {
	
	@Autowired ProductService productService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String writeProduct() {
		
		return "product";
	}
	

	@RequestMapping(value="/user/addproductimage", method = RequestMethod.POST)
	@ResponseBody
	public Product addProductImage(@RequestParam(value = "productId", required = false) String productId, @RequestParam(value = "fileToUpload", required = false) CommonsMultipartFile fileToUpload, 
			MultipartHttpServletRequest mrequest) throws IOException {
		
		Product product = productService.findById(productId);
		productService.addProductImage(product, fileToUpload);

		return product;
	}
	
	
	@RequestMapping(value="/product/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product getProduct(@PathVariable("id") String id) throws IOException {
		
		return productService.findById(id);
	}	
	
	@RequestMapping(value="/product", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> products() throws Exception {
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			return productService.findAllProductsByStatus(ProductStatus.PUBLISHED, user.getId());
		}

		return null;
	}
	
	@RequestMapping(value="/myproduct", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> myProducts() throws Exception {
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			return productService.findAllProductsByStatusAndUser(ProductStatus.PUBLISHED, user.getId());
		}

		return null;
	}
	
	@RequestMapping(value="/product/draft", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> drafts() throws Exception {
		
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			return productService.findAllProductsByStatusAndUser(ProductStatus.DRAFT, user.getId());
		}

		return null;
	}
	
	
	@RequestMapping(value="/user/draft", method = RequestMethod.POST)
	@ResponseBody
	public Product createDraftProduct(@RequestBody Product product) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		product.setUserId(user.getId());
		product.setProductStatus(ProductStatus.DRAFT);
		if(product.getProductText() != null && product.getProductText().length() > 300) {
			product.setProductDescription(product.getProductText().substring(0, 300));
		}
		else if(product.getProductText() != null){
			product.setProductDescription(product.getProductText());
		}
		product.setUserFullName(user.getFirstName() + " " + user.getLastName());
		product.setUserPhotoLink(user.getPhotoLink());
		Product newProduct = productService.createProduct(product);
		
		return newProduct;
	}

	
	@RequestMapping(value="/user/createproduct", method = RequestMethod.POST)
	@ResponseBody
	public Product createProduct(@RequestBody Product product) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		product.setUserId(user.getId());
		product.setProductStatus(ProductStatus.PUBLISHED);
		if(product.getProductText() != null && product.getProductText().length() > 300) {
			product.setProductDescription(product.getProductText().substring(0, 300));
		}
		else if(product.getProductText() != null){
			product.setProductDescription(product.getProductText());
		}
		product.setUserFullName(user.getFirstName() + " " + user.getLastName());
		product.setUserPhotoLink(user.getPhotoLink());
		Product newProduct = productService.createProduct(product);
		//user.getProductId().add(newProduct.getId());
		//userService.updateUser(user);
		return newProduct;
	}

	@RequestMapping(value="/user/{id}/image", method = RequestMethod.POST)
	@ResponseBody
	public Product addProductImage(@PathVariable("id") String id, @RequestParam(value = "file", required = false) List<CommonsMultipartFile> files, 
			MultipartHttpServletRequest mrequest) throws IOException {
		for (CommonsMultipartFile commonsMultipartFile : files) {
			System.out.println(commonsMultipartFile.getName());
			Product product = productService.findById(id);
			if(product != null) {
				productService.addProductImage(product, commonsMultipartFile);
			}
		}


		return null;
	}
	
	@RequestMapping(value="/product/{id}/comment", method = RequestMethod.POST)
	@ResponseBody
	public Product addProductComment(@PathVariable("id") String id, @RequestBody String comment) throws Exception {
		Product product = productService.findById(id); 
		AccountUser user = userService.getLoggedInUser();
		Comment newComment = new Comment(comment,user.getId(), user.getFirstName()+ " " + user.getLastName(), new Date());
		product.getComment().add(newComment);
		product = productService.createProduct(product);
		return product;
	}
	
	@RequestMapping(value="/product/{id}/image", method = RequestMethod.GET)
	@ResponseBody
	public byte[][] getProductImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		int i = 0;
		List<GridFSDBFile> imageForOutput = productService.getProductImage(id);
		byte[][] images = new byte[imageForOutput.size()][];
		
		for (GridFSDBFile gridFSDBFile : imageForOutput) {
			if(imageForOutput != null) {
				InputStream is = gridFSDBFile.getInputStream();

				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[16384];
				
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				
				buffer.flush();
				byte[] imagenEnBytes = buffer.toByteArray();

				response.setHeader("Accept-ranges", "bytes");
				response.setContentType("image/jpeg");
				response.setContentLength(imagenEnBytes.length);
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Content-Description", "File Transfer");
				response.setHeader("Content-Transfer-Encoding:", "binary");

				OutputStream out = response.getOutputStream();
				out.write(imagenEnBytes);
				out.flush();
				out.close();
		
				images[i] = data;
				i++;
			}
		}

		
		return images;
	}
	
}
