package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.db.repository.IProductRepository;
import com.jewelhaat.model.Product;
import com.jewelhaat.model.ProductStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Service
@Qualifier("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired IProductRepository productRepository;
	
	public Product createProduct(Product product) throws IOException {

		return productRepository.createProduct(product);
	}

	public Product findById(String productId) {
		return productRepository.findById(productId);
	}

	public void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException {
		
		productRepository.addProductImage(product, fileToUpload);
		
	}

	public List<Product> searchProductByKeyWord(SearchCriteria searchCriteria) {
		return productRepository.searchProductByKeyWord(searchCriteria);
	}

	public List<GridFSDBFile> getProductImage(String imageId) {
		return productRepository.getProductImage(imageId);
	}

	public List<Product> findAllProductsByStatus(ProductStatus status, String userId) {
		return productRepository.findAllProductsByStatus(status);
	}

	public List<Product> findAllProductsByStatusAndUser(ProductStatus status, String userId) {
		return productRepository.findAllProductsByStatusAndUser(status, userId);
	}


}
