package com.jewelhaat.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Product;
import com.jewelhaat.model.ProductStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public interface IProductRepository {
	
    public Product createProduct(Product user) throws IOException;
    
    public Product findById(String id);
    
    public Product findByUserName(String userName);
     
    public void update(Product user);

	public void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Product> searchProductByKeyWord(SearchCriteria searchCriteria);

	public List<GridFSDBFile> getProductImage(String imageId);

	public List<Product> findAllProductsByStatus(ProductStatus status);

	public List<Product> findAllProductsByStatusAndUser(ProductStatus status, String userId);
}
