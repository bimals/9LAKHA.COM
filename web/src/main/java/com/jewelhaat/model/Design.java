package com.jewelhaat.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Design implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private String id;
	private String designName;
	private String designDescription;
	private UUID designUUID;
	private String userId;
	private String imageId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesignName() {
		return designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	public String getDesignDescription() {
		return designDescription;
	}
	public void setDesignDescription(String designDescription) {
		this.designDescription = designDescription;
	}
	public UUID getDesignUUID() {
		return designUUID;
	}
	public void setDesignUUID(UUID designUUID) {
		this.designUUID = designUUID;
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
