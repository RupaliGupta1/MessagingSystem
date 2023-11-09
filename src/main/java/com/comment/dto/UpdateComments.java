package com.comment.dto;

public class UpdateComments 
{
	private String commentFrom;
	 private String commentTo;
	    private String message;
	    private String updateMessage;
	    private String commentDateTime;
		public String getUpdateMessage() {
			return updateMessage;
		}
		public void setUpdateMessage(String updateMessage) {
			this.updateMessage = updateMessage;
		}
		public String getCommentFrom() {
			return commentFrom;
		}
		public void setCommentFrom(String commentFrom) {
			this.commentFrom = commentFrom;
		}
		public String getCommentTo() {
			return commentTo;
		}
		public void setCommentTo(String commentTo) {
			this.commentTo = commentTo;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getCommentDateTime() {
			return commentDateTime;
		}
		public void setCommentDateTime(String commentDateTime) {
			this.commentDateTime = commentDateTime;
		}
	    
	    
	    

}
