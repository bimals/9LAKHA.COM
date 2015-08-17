package blog.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import blog.model.Product;
import blog.model.SearchCriteria;

@Repository
public interface IProductRepository {
	
    public Product createProduct(Product user) throws IOException;
    
    public Product findById(String id);
    
    public Product findByUserName(String userName);
     
    public void update(Product user);

	public void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Product> searchProductByKeyWord(SearchCriteria searchCriteria);

	public GridFSDBFile getProductImage(String imageId);
}
