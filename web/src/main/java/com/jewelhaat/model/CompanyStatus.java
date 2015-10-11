package com.jewelhaat.model;

public enum CompanyStatus {
	DRAFT("DRAFT"),
	TRASH("TRASH"),
	PUBLISHED("PUBLISHED");
	
	String status = null;
	
	CompanyStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
