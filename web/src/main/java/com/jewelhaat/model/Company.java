package com.jewelhaat.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBList;

public class Company implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String companyName;
	private String companyURL;
	private String businessLegalName;
	private String companyEmail;
	private BasicDBList products;
	private String contactPerson;
	private String phoneNumber;
	private UUID companyUUID;
	private String userId;
	private String imageId;
	private String addressFullName;
	private String addressLine1;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String cardHolderName;
	private String cardNumber;
	private String expireMonth;
	private String expireYear;
	private String cvv;
	private BasicDBList creditCards;
	private String planType;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyURL() {
		return companyURL;
	}
	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
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
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
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
	public UUID getCompanyUUID() {
		return companyUUID;
	}
	public void setCompanyUUID(UUID companyUUID) {
		this.companyUUID = companyUUID;
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
	public String getAddressFullName() {
		return addressFullName;
	}
	public void setAddressFullName(String addressFullName) {
		this.addressFullName = addressFullName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpireMonth() {
		return expireMonth;
	}
	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}
	public String getExpireYear() {
		return expireYear;
	}
	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public BasicDBList getCreditCards() {
		if(creditCards == null) {
			creditCards = new BasicDBList();
		}
		return creditCards;
	}
	public void setCreditCards(BasicDBList creditCards) {
		this.creditCards = creditCards;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
		
}
