package com.jewelhaat.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.db.repository.ICompanyRepository;
import com.jewelhaat.model.Company;
import com.jewelhaat.model.CompanyStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Service
@Qualifier("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired ICompanyRepository companyRepository;
	
	public Company createCompany(Company company) throws IOException {

		return companyRepository.createCompany(company);
	}

	public Company findById(String companyId) {
		return companyRepository.findById(companyId);
	}

	public void addCompanyImage(Company company, CommonsMultipartFile fileToUpload) throws IOException {
		
		companyRepository.addCompanyImage(company, fileToUpload);
		
	}

	public List<Company> searchCompanyByKeyWord(SearchCriteria searchCriteria) {
		return companyRepository.searchCompanyByKeyWord(searchCriteria);
	}

	public List<GridFSDBFile> getCompanyImage(String imageId) {
		return companyRepository.getCompanyImage(imageId);
	}

	public List<Company> findAllCompanysByStatus(CompanyStatus status, String userId) {
		return companyRepository.findAllCompanysByStatus(status);
	}

	public List<Company> findAllCompanysByStatusAndUser(CompanyStatus status, String userId) {

		return companyRepository.findAllCompanysByStatusAndUser(status, userId);
	}


}
