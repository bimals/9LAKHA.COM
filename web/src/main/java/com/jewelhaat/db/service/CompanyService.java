package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Company;
import com.jewelhaat.model.CompanyStatus;
import com.mongodb.gridfs.GridFSDBFile;

public interface CompanyService {
	
	Company createCompany(Company company) throws IOException;

	Company findById(String companyId);

	void addCompanyImage(Company company, CommonsMultipartFile fileToUpload) throws IOException;

	List<Company> searchCompanyByKeyWord(SearchCriteria searchCriteria);

	List<GridFSDBFile> getCompanyImage(String imageId);

	List<Company> findAllCompanysByStatus(CompanyStatus published, String id);

	List<Company> findAllCompanysByStatusAndUser(CompanyStatus draft, String id);

}
