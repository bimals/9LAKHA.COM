package com.jewelhaat.model;

public enum ProductStatus {
	DRAFT("DRAFT"),
	TRASH("TRASH"),
	PUBLISHED("PUBLISHED");
	
	String status = null;
	
	ProductStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
