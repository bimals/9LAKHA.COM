package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.db.repository.ISellerRepository;
import com.jewelhaat.model.Seller;
import com.jewelhaat.model.SellerStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Service
@Qualifier("sellerService")
public class SellerServiceImpl implements SellerService {

	@Autowired ISellerRepository sellerRepository;
	
	public Seller createSeller(Seller seller) throws IOException {

		return sellerRepository.createSeller(seller);
	}

	public Seller findById(String sellerId) {
		return sellerRepository.findById(sellerId);
	}

	public void addSellerImage(Seller seller, CommonsMultipartFile fileToUpload) throws IOException {
		
		sellerRepository.addSellerImage(seller, fileToUpload);
		
	}

	public List<Seller> searchSellerByKeyWord(SearchCriteria searchCriteria) {
		return sellerRepository.searchSellerByKeyWord(searchCriteria);
	}

	public List<GridFSDBFile> getSellerImage(String imageId) {
		return sellerRepository.getSellerImage(imageId);
	}

	public List<Seller> findAllSellersByStatus(SellerStatus status, String userId) {
		return sellerRepository.findAllSellersByStatus(status);
	}

	public List<Seller> findAllSellersByStatusAndUser(SellerStatus status, String userId) {
		return sellerRepository.findAllSellersByStatusAndUser(status, userId);
	}


}
