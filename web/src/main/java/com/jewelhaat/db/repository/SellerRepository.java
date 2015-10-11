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
import com.jewelhaat.model.Seller;
import com.jewelhaat.model.SellerStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
@Qualifier("sellerRepository")
public class SellerRepository implements ISellerRepository{
	
	@Autowired private MongoOperations mongoOps;
	private static final String PRODUCT_COLLECTION = "Seller";

	public Seller createSeller(Seller seller) throws IOException {
		UUID sellerUUID = UUID.randomUUID();
		seller.setSellerUUID(sellerUUID);
		this.mongoOps.insert(seller, PRODUCT_COLLECTION);
		return this.findByUUID(sellerUUID);
	}

	public Seller findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, Seller.class, PRODUCT_COLLECTION);
	}

	public Seller findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Seller findByUUID(UUID uuid) {
        Query query = new Query(Criteria.where("sellerUUID").is(uuid));
        return this.mongoOps.findOne(query, Seller.class, PRODUCT_COLLECTION);
	}

	public void update(Seller p) {
		this.mongoOps.save(p, PRODUCT_COLLECTION);		
	}

	public void addSellerImage(Seller seller, CommonsMultipartFile fileToUpload) throws IOException {
/*        String fileName = seller.getId() + "-" + seller.getSellerUUID();
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(fileName);
		gfsFile.setContentType(fileToUpload.getContentType());
		
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		
		seller.setImageId(imageForOutput.getId().toString());
		this.update(seller);*/
		
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(seller.getId());
		gfsFile.setContentType(fileToUpload.getContentType());
		
		BasicDBObject fileMetadata = new BasicDBObject();
		fileMetadata.put("fileName", fileToUpload.getName());
		fileMetadata.put("originalFileName", fileToUpload.getOriginalFilename());
		fileMetadata.put("contentType", fileToUpload.getContentType());
		fileMetadata.put("fileSize", fileToUpload.getSize());
		fileMetadata.put("storageDescription", fileToUpload.getStorageDescription());
		fileMetadata.put("userId", seller.getUserId());
		
		gfsFile.setMetaData(fileMetadata);
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(seller.getId());
		
		seller.setImageId(imageForOutput.getId().toString());
		this.update(seller);
	}

	public List<Seller> searchSellerByKeyWord(SearchCriteria searchCriteria) {
		String searchText = "";
		int skip = 0;
		if(searchCriteria != null && searchCriteria.getSearchText() != null) {
			searchText = searchCriteria.getSearchText();
		}
		
		if(searchCriteria != null && searchCriteria.getSkip() != 0) {
			skip = searchCriteria.getSkip();
		}
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Criteria criteria = Criteria.where("sellerTitle").regex(regex).orOperator(Criteria.where("sellerDescription").regex(regex));
		
		if(searchCriteria.getAfter() != null) {
			criteria = Criteria.where("_id").gt(searchCriteria.getAfter()).andOperator(criteria);
		}

		Query query = new Query(criteria).limit(20).with(new Sort(Direction.DESC, "_id")).skip(skip);
		return mongoOps.find(query, Seller.class, PRODUCT_COLLECTION);
	}

	public List<GridFSDBFile> getSellerImage(String imageId) {
		DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		return gfsPhoto.find(imageId);
	}

	public List<Seller> findAllSellersByStatus(SellerStatus status) {
        Query query = new Query(Criteria.where("sellerStatus").is(status)).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Seller.class, PRODUCT_COLLECTION);
	}

	public List<Seller> findAllSellersByStatusAndUser(SellerStatus status, String userId) {
        Query query = new Query(Criteria.where("sellerStatus").is(status).andOperator(Criteria.where("userId").is(userId))).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Seller.class, PRODUCT_COLLECTION);
	}

}
