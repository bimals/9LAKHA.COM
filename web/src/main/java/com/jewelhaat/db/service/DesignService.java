package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Design;
import com.mongodb.gridfs.GridFSDBFile;

public interface DesignService {
	
	Design createDesign(Design Design) throws IOException;

	Design findById(String DesignId);

	void addDesignImage(Design Design, CommonsMultipartFile fileToUpload) throws IOException;

	List<Design> searchDesignByKeyWord(SearchCriteria searchCriteria);

	GridFSDBFile getDesignImage(String imageId);

}
