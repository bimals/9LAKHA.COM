package blog.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import blog.model.Design;
import blog.model.SearchCriteria;

@Repository
public interface IDesignRepository {
	
    public Design createDesign(Design user) throws IOException;
    
    public Design findById(String id);
    
    public Design findByUserName(String userName);
     
    public void update(Design user);

	public void addDesignImage(Design product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Design> searchDesignByKeyWord(SearchCriteria searchCriteria);

	public GridFSDBFile getDesignImage(String imageId);
}
