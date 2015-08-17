package blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import blog.db.repository.IDesignRepository;
import blog.model.Design;
import blog.model.SearchCriteria;

@Service
@Qualifier("designService")
public class DesignServiceImpl implements DesignService {

	@Autowired IDesignRepository designRepository;
	
	public Design createDesign(Design design) throws IOException {

		return designRepository.createDesign(design);
	}

	public Design findById(String designId) {
		return designRepository.findById(designId);
	}

	public void addDesignImage(Design design, CommonsMultipartFile fileToUpload) throws IOException {
		
		designRepository.addDesignImage(design, fileToUpload);
		
	}

	public List<Design> searchDesignByKeyWord(SearchCriteria searchCriteria) {
		return designRepository.searchDesignByKeyWord(searchCriteria);
	}

	public GridFSDBFile getDesignImage(String imageId) {
		return designRepository.getDesignImage(imageId);
	}


}
