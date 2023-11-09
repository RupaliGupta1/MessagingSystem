package com.comment.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Username must contain only alphabetic characters") 
	private String name;
	@NotNull
	private String lastActiveDateTime;
	
	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastActiveDateTime() {
		return lastActiveDateTime;
	}


	public void setLastActiveDateTime(String lastActiveDateTime) {
		this.lastActiveDateTime = lastActiveDateTime;
	}


	public User(Long userId, String name, String lastActiveDateTime) {
		this.userId = userId;
		this.name = name;
		this.lastActiveDateTime = lastActiveDateTime;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
