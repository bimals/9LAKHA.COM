package com.jewelhaat.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Seller;
import com.jewelhaat.model.SellerStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public interface ISellerRepository {
	
    public Seller createSeller(Seller user) throws IOException;
    
    public Seller findById(String id);
    
    public Seller findByUserName(String userName);
     
    public void update(Seller user);

	public void addSellerImage(Seller product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Seller> searchSellerByKeyWord(SearchCriteria searchCriteria);

	public List<GridFSDBFile> getSellerImage(String imageId);

	public List<Seller> findAllSellersByStatus(SellerStatus status);

	public List<Seller> findAllSellersByStatusAndUser(SellerStatus status, String userId);
}
