package com.jewelhaat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBList;

public class Product implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String productTitle;
	private String productWebsite;
	private String productYouTube;
	private String productText;
	private String productMoreText;
	private String productName;
	private String productDescription;
	private UUID productUUID;
	private String userId;
	private String userFullName;
	private String userPhotoLink;
	private String imageId;
	private ProductStatus productStatus;
	private Date creationTimeStamp;
	private BasicDBList comment;
	private BasicDBList likes;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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
	public UUID getProductUUID() {
		return productUUID;
	}
	public void setProductUUID(UUID productUUID) {
		this.productUUID = productUUID;
	}
	public ProductStatus getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductWebsite() {
		return productWebsite;
	}
	public void setProductWebsite(String productWebsite) {
		this.productWebsite = productWebsite;
	}
	public String getProductYouTube() {
		return productYouTube;
	}
	public void setProductYouTube(String productYouTube) {
		this.productYouTube = productYouTube;
	}
	public String getProductText() {
		return productText;
	}
	public void setProductText(String productText) {
		this.productText = productText;
	}
	public String getProductMoreText() {
		return productMoreText;
	}
	public void setProductMoreText(String productMoreText) {
		this.productMoreText = productMoreText;
	}
	public Date getCreationTimeStamp() {
		return creationTimeStamp;
	}
	public void setCreationTimeStamp(Date creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserPhotoLink() {
		return userPhotoLink;
	}
	public void setUserPhotoLink(String userPhotoLink) {
		this.userPhotoLink = userPhotoLink;
	}
	public BasicDBList getComment() {
		if(comment == null) {
			comment = new BasicDBList();
		}
		return comment;
	}
	public void setComment(BasicDBList comment) {
		this.comment = comment;
	}
	public BasicDBList getLikes() {
		if(likes == null) {
			likes = new BasicDBList();
		}
		return likes;
	}
	public void setLikes(BasicDBList likes) {
		this.likes = likes;
	}

}
