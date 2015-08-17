package blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import blog.model.Product;
import blog.model.SearchCriteria;

public interface ProductService {
	
	Product createProduct(Product product) throws IOException;

	Product findById(String productId);

	void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException;

	List<Product> searchProductByKeyWord(SearchCriteria searchCriteria);

	GridFSDBFile getProductImage(String imageId);

}
