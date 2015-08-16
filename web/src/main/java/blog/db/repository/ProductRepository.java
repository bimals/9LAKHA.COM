package blog.db.repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import blog.model.AccountUser;
import blog.model.Product;

@Repository
@Qualifier("productRepository")
public class ProductRepository implements IProductRepository{
	
	@Autowired private MongoOperations mongoOps;
	private static final String PRODUCT_COLLECTION = "Product";

	public Product createProduct(Product product) throws IOException {
		UUID productUUID = UUID.randomUUID();
		product.setProductUUID(productUUID);
		this.mongoOps.insert(product, PRODUCT_COLLECTION);
		return this.findByUUID(productUUID);
	}

	public Product findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, Product.class, PRODUCT_COLLECTION);
	}

	public Product findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product findByUUID(UUID uuid) {
        Query query = new Query(Criteria.where("productUUID").is(uuid));
        return this.mongoOps.findOne(query, Product.class, PRODUCT_COLLECTION);
	}

	public void update(Product p) {
		this.mongoOps.save(p, PRODUCT_COLLECTION);		
	}

	public void addProductImage(Product product, CommonsMultipartFile fileToUpload) throws IOException {
        String fileName = product.getId() + "-" + product.getProductUUID();
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(fileName);
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		
		product.setImageId(imageForOutput.getId().toString());
		this.update(product);
	}

	public List<Product> searchProductByKeyWord(String searchText) {
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Query query = new Query(Criteria.where("productName").regex(regex).orOperator(Criteria.where("productDescription").regex(regex)));
		return mongoOps.find(query, Product.class);
	}

}
