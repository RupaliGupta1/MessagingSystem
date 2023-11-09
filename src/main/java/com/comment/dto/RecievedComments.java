package com.comment.dto;

public class RecievedComments
{
	
    private String commentFrom;
    private String message;
    private String commentDateTime;
    
	public RecievedComments(String commentFrom, String commentDateTime) {
		this.commentFrom = commentFrom;
		this.commentDateTime = commentDateTime;
	}
	public RecievedComments(String commentFrom, String message, String commentDateTime) {
		this.commentFrom = commentFrom;
		this.message = message;
		this.commentDateTime = commentDateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCommentFrom() {
		return commentFrom;
	}
	public void setCommentFrom(String commentFrom) {
		this.commentFrom = commentFrom;
	}
	public String getCommentDateTime() {
		return commentDateTime;
	}
	public void setCommentDateTime(String commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
	public RecievedComments() {
		// TODO Auto-generated constructor stub
	}
	
    
    

}
