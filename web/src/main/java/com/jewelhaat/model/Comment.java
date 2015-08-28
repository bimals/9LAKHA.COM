package com.jewelhaat.model;

import java.util.Date;

import com.mongodb.BasicDBObject;

public class Comment extends BasicDBObject{

	private String commentText;
	private String userId;
	private String userFullName;
	private Date commentTime;
	
	
	public Comment(String commentText, String userId, String userFullName, Date commentTime) {
		super();
		this.commentText = commentText;
		this.userId = userId;
		this.userFullName = userFullName;
		
		super.put("commentText", commentText);
		super.put("userId", userId);
		super.put("userFullName", userFullName);
		super.put("commentTime", commentTime);
	}
		
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	
}
