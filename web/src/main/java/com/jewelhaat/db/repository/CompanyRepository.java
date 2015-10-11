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
import com.jewelhaat.model.Company;
import com.jewelhaat.model.CompanyStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
@Qualifier("companyRepository")
public class CompanyRepository implements ICompanyRepository{
	
	@Autowired private MongoOperations mongoOps;
	private static final String COMPANY_COLLECTION = "Company";

	public Company createCompany(Company company) throws IOException {
		UUID companyUUID = UUID.randomUUID();
		company.setCompanyUUID(companyUUID);
		this.mongoOps.save(company, COMPANY_COLLECTION);
		return this.findByUUID(companyUUID);
	}

	public Company findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, Company.class, COMPANY_COLLECTION);
	}

	public Company findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Company findByUUID(UUID uuid) {
        Query query = new Query(Criteria.where("companyUUID").is(uuid));
        return this.mongoOps.findOne(query, Company.class, COMPANY_COLLECTION);
	}

	public void update(Company p) {
		this.mongoOps.save(p, COMPANY_COLLECTION);		
	}

	public void addCompanyImage(Company company, CommonsMultipartFile fileToUpload) throws IOException {
/*        String fileName = company.getId() + "-" + company.getCompanyUUID();
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(fileName);
		gfsFile.setContentType(fileToUpload.getContentType());
		
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		
		company.setImageId(imageForOutput.getId().toString());
		this.update(company);*/
		
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(company.getId());
		gfsFile.setContentType(fileToUpload.getContentType());
		
		BasicDBObject fileMetadata = new BasicDBObject();
		fileMetadata.put("fileName", fileToUpload.getName());
		fileMetadata.put("originalFileName", fileToUpload.getOriginalFilename());
		fileMetadata.put("contentType", fileToUpload.getContentType());
		fileMetadata.put("fileSize", fileToUpload.getSize());
		fileMetadata.put("storageDescription", fileToUpload.getStorageDescription());
		fileMetadata.put("userId", company.getUserId());
		
		gfsFile.setMetaData(fileMetadata);
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(company.getId());
		
		company.setImageId(imageForOutput.getId().toString());
		this.update(company);
	}

	public List<Company> searchCompanyByKeyWord(SearchCriteria searchCriteria) {
		String searchText = "";
		int skip = 0;
		if(searchCriteria != null && searchCriteria.getSearchText() != null) {
			searchText = searchCriteria.getSearchText();
		}
		
		if(searchCriteria != null && searchCriteria.getSkip() != 0) {
			skip = searchCriteria.getSkip();
		}
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Criteria criteria = Criteria.where("companyTitle").regex(regex).orOperator(Criteria.where("companyDescription").regex(regex));
		
		if(searchCriteria.getAfter() != null) {
			criteria = Criteria.where("_id").gt(searchCriteria.getAfter()).andOperator(criteria);
		}

		Query query = new Query(criteria).limit(20).with(new Sort(Direction.DESC, "_id")).skip(skip);
		return mongoOps.find(query, Company.class, COMPANY_COLLECTION);
	}

	public List<GridFSDBFile> getCompanyImage(String imageId) {
		DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		return gfsPhoto.find(imageId);
	}

	public List<Company> findAllCompanysByStatus(CompanyStatus status) {
        Query query = new Query(Criteria.where("companyStatus").is(status)).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Company.class, COMPANY_COLLECTION);
	}

	public List<Company> findAllCompanysByStatusAndUser(CompanyStatus status, String userId) {
        Query query = new Query(Criteria.where("companyStatus").is(status).andOperator(Criteria.where("userId").is(userId))).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Company.class, COMPANY_COLLECTION);
	}

}
