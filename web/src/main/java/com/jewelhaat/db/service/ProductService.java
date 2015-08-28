package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Product;
import com.jewelhaat.model.ProductStatus;
import com.mongodb.gridfs.GridFSDBFile;

public interface ProductService {
	
	Product createProduct(Product product) throws IOException;

	Product findById(String productId);

	void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException;

	List<Product> searchProductByKeyWord(SearchCriteria searchCriteria);

	List<GridFSDBFile> getProductImage(String imageId);

	List<Product> findAllProductsByStatus(ProductStatus published, String id);

	List<Product> findAllProductsByStatusAndUser(ProductStatus draft, String id);

}
