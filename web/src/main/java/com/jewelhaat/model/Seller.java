package com.jewelhaat.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBList;

public class Seller implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String sellerName;
	private String sellerURL;
	private String businessLegalName;
	private String sellerEmail;
	private BasicDBList products;
	private String contactPerson;
	private String phoneNumber;
	private UUID sellerUUID;
	private String userId;
	private String imageId;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerURL() {
		return sellerURL;
	}
	public void setSellerURL(String sellerURL) {
		this.sellerURL = sellerURL;
	}
	public String getBusinessLegalName() {
		return businessLegalName;
	}
	public void setBusinessLegalName(String businessLegalName) {
		this.businessLegalName = businessLegalName;
	}
	public BasicDBList getProducts() {
		return products;
	}
	public void setProducts(BasicDBList products) {
		this.products = products;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public UUID getSellerUUID() {
		return sellerUUID;
	}
	public void setSellerUUID(UUID sellerUUID) {
		this.sellerUUID = sellerUUID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
		
}
