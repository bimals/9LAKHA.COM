package com.jewelhaat.model;

public enum SellerStatus {
	DRAFT("DRAFT"),
	TRASH("TRASH"),
	PUBLISHED("PUBLISHED");
	
	String status = null;
	
	SellerStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
