package com.comment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
@Entity
public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	@NotNull
	private String message;
	@NotNull
	private String commentDateTime;
	@NotNull
	@ManyToOne
	private User postedBy; //userid of user table
	@NotNull
	private String commentFrom;
	@NotNull
	private String commentTo;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
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
	public Comment(Long commentId, @NotNull String message, @NotNull String commentDateTime, @NotNull User postedBy,
			@NotNull String commentFrom, @NotNull String commentTo) {
		this.commentId = commentId;
		this.message = message;
		this.commentDateTime = commentDateTime;
		this.postedBy = postedBy;
		this.commentFrom = commentFrom;
		this.commentTo = commentTo;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	

}
