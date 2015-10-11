package com.jewelhaat.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.model.Company;
import com.jewelhaat.model.CompanyStatus;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public interface ICompanyRepository {
	
    public Company createCompany(Company user) throws IOException;
    
    public Company findById(String id);
    
    public Company findByUserName(String userName);
     
    public void update(Company user);

	public void addCompanyImage(Company product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Company> searchCompanyByKeyWord(SearchCriteria searchCriteria);

	public List<GridFSDBFile> getCompanyImage(String imageId);

	public List<Company> findAllCompanysByStatus(CompanyStatus status);

	public List<Company> findAllCompanysByStatusAndUser(CompanyStatus status, String userId);
}
