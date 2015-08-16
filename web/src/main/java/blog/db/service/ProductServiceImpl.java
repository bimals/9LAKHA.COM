package blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.db.repository.IProductRepository;
import blog.model.Product;

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

	public List<Product> searchProductByKeyWord(String searchText) {
		return productRepository.searchProductByKeyWord(searchText);
	}

}
