package com.jewelhaat.db.repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Design;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
@Qualifier("designRepository")
public class DesignRepository implements IDesignRepository{
	
	@Autowired private MongoOperations mongoOps;
	private static final String DESIGN_COLLECTION = "Design";

	public Design createDesign(Design design) throws IOException {
		UUID designUUID = UUID.randomUUID();
		design.setDesignUUID(designUUID);
		this.mongoOps.insert(design, DESIGN_COLLECTION);
		return this.findByUUID(designUUID);
	}

	public Design findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, Design.class, DESIGN_COLLECTION);
	}

	public Design findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Design findByUUID(UUID uuid) {
        Query query = new Query(Criteria.where("designUUID").is(uuid));
        return this.mongoOps.findOne(query, Design.class, DESIGN_COLLECTION);
	}

	public void update(Design p) {
		this.mongoOps.save(p, DESIGN_COLLECTION);		
	}

	public void addDesignImage(Design design, CommonsMultipartFile fileToUpload) throws IOException {
        String fileName = design.getId() + "-" + design.getDesignUUID();
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(fileName);
		gfsFile.setContentType(fileToUpload.getContentType());
		
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
		
		design.setImageId(imageForOutput.getId().toString());
		this.update(design);
	}

	public List<Design> searchDesignByKeyWord(SearchCriteria searchCriteria) {
		String searchText = "";
		int skip = 0;
		if(searchCriteria != null && searchCriteria.getSearchText() != null) {
			searchText = searchCriteria.getSearchText();
		}
		
		if(searchCriteria != null && searchCriteria.getSkip() != 0) {
			skip = searchCriteria.getSkip();
		}
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Criteria criteria = Criteria.where("designName").regex(regex).orOperator(Criteria.where("designDescription").regex(regex));
		
		if(searchCriteria.getAfter() != null) {
			criteria = Criteria.where("_id").gt(searchCriteria.getAfter()).andOperator(criteria);
		}

		Query query = new Query(criteria).limit(20).with(new Sort("_id"));
		return mongoOps.find(query, Design.class, DESIGN_COLLECTION);
	}

	public GridFSDBFile getDesignImage(String imageId) {
		DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		return gfsPhoto.findOne(imageId);
	}

}
