package com.jewelhaat.db.repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Product;
import com.jewelhaat.model.ProductStatus;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

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
		gfsFile.setContentType(fileToUpload.getContentType());
		
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		
		product.setImageId(imageForOutput.getId().toString());
		this.update(product);
	}

	public List<Product> searchProductByKeyWord(SearchCriteria searchCriteria) {
		String searchText = "";
		int skip = 0;
		if(searchCriteria != null && searchCriteria.getSearchText() != null) {
			searchText = searchCriteria.getSearchText();
		}
		
		if(searchCriteria != null && searchCriteria.getSkip() != 0) {
			skip = searchCriteria.getSkip();
		}
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Criteria criteria = Criteria.where("productName").regex(regex).orOperator(Criteria.where("productDescription").regex(regex));
		
		if(searchCriteria.getAfter() != null) {
			criteria = Criteria.where("_id").gt(searchCriteria.getAfter()).andOperator(criteria);
		}

		Query query = new Query().limit(20).with(new Sort(Direction.DESC, "_id"));
		return mongoOps.find(query, Product.class, PRODUCT_COLLECTION);
	}

	public List<GridFSDBFile> getProductImage(String imageId) {
		DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		return gfsPhoto.find(imageId);
	}

	public List<Product> findAllProductsByStatus(ProductStatus status) {
        Query query = new Query(Criteria.where("productStatus").is(status)).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Product.class, PRODUCT_COLLECTION);
	}

	public List<Product> findAllProductsByStatusAndUser(ProductStatus status, String userId) {
        Query query = new Query(Criteria.where("productStatus").is(status).andOperator(Criteria.where("userId").is(userId))).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Product.class, PRODUCT_COLLECTION);
	}

}
