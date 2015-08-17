package blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import blog.model.Design;
import blog.model.SearchCriteria;

public interface DesignService {
	
	Design createDesign(Design Design) throws IOException;

	Design findById(String DesignId);

	void addDesignImage(Design Design, CommonsMultipartFile fileToUpload) throws IOException;

	List<Design> searchDesignByKeyWord(SearchCriteria searchCriteria);

	GridFSDBFile getDesignImage(String imageId);

}
