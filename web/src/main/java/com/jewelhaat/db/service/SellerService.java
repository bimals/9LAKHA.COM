package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Seller;
import com.jewelhaat.model.SellerStatus;
import com.mongodb.gridfs.GridFSDBFile;

public interface SellerService {
	
	Seller createSeller(Seller seller) throws IOException;

	Seller findById(String sellerId);

	void addSellerImage(Seller seller, CommonsMultipartFile fileToUpload) throws IOException;

	List<Seller> searchSellerByKeyWord(SearchCriteria searchCriteria);

	List<GridFSDBFile> getSellerImage(String imageId);

	List<Seller> findAllSellersByStatus(SellerStatus published, String id);

	List<Seller> findAllSellersByStatusAndUser(SellerStatus draft, String id);

}
