package blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.model.Product;

public interface ProductService {
	
	Product createProduct(Product product) throws IOException;

	Product findById(String productId);

	void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException;

	List<Product> searchProductByKeyWord(String searchText);

}
