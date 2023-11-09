package com.comment.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.comment.Entity.Comment;
import com.comment.Entity.User;
import com.comment.dto.CommentDto;
import com.comment.dto.RecievedComments;
import com.comment.dto.SentComments;
import com.comment.dto.UpdateComments;
import com.comment.exceptions.NoCommentException;
import com.comment.repository.CommentRepository;
import com.comment.repository.UserRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@Service
public class CommentService {
	
	@Autowired 
   private CommentRepository commentRepository;
	
	@Autowired 
	private UserRepository userRepository;

	private User user;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();
	
	public ResponseEntity<String> addComment(CommentDto commentDto)
	{
		
		User commentFrom=userRepository.findByName(commentDto.getCommentFrom());
		User commentTo=userRepository.findByName(commentDto.getCommentTo());
	
		System.out.println(commentFrom+" "+commentTo);
		if(commentFrom!=null)
		{
			commentFrom.setLastActiveDateTime(dtf.format(now));
			userRepository.save(commentFrom);
			this.user=commentFrom;
			

		}
		else {
			 User userFrom=	createUserCommentFrom(commentDto);
			 this.user=userFrom;
		}
		
		if(commentTo==null)
		{
			createUser(commentDto);
		}

		Comment comment=new Comment();
		comment.setCommentDateTime(dtf.format(now));
		comment.setCommentFrom(commentDto.getCommentFrom());
		comment.setCommentTo(commentDto.getCommentTo());
		comment.setMessage(commentDto.getMessage());
		comment.setPostedBy(user);
		
		try {
			commentRepository.save(comment);
		} catch (ConstraintViolationException e) {
			 for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			        String message = violation.getMessage();
			        String propertyPath = violation.getPropertyPath().toString();
			        // Handle the violation details, which in this case will contain a message like "Username should only contain letters"
			        System.out.println("Constraint Violation: " + propertyPath + " - " + message);
			    }
		}
		
		

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	private void createUser(CommentDto commentDto) {
	      User user=new User();

		user.setName(commentDto.getCommentTo());
		user.setLastActiveDateTime(dtf.format(now));
		userRepository.save(user);

	}

	private User createUserCommentFrom(CommentDto commentDto) {
      User user=new User();
		
		user.setName(commentDto.getCommentFrom());
		user.setLastActiveDateTime(dtf.format(now));
		userRepository.save(user);
        
		return user;
	}

	public List<SentComments> getSentComments(String commentFrom)throws NoCommentException{
		List<SentComments> sentComments=new ArrayList<>();
		List<Comment> comments=commentRepository.findByCommentFrom(commentFrom);
		if(comments.isEmpty())
		{
			throw new NoCommentException("You have not don any comment till date.....");
		}
		for (Comment comment : comments) {
			SentComments sentComment=new SentComments();
			sentComment.setCommentDateTime(comment.getCommentDateTime());
			sentComment.setCommentTo(comment.getCommentTo());
			sentComment.setMessage(comment.getMessage());
			sentComments.add(sentComment);
		}
		
		return sentComments;
	}

	public List<RecievedComments> getRecievedComments(String commentTo) throws NoCommentException 
	{
		List<RecievedComments> recievedComments=new ArrayList<>();
		List<Comment> comments= commentRepository.findByCommentTo(commentTo);
		
		if(comments.isEmpty())
		{
			throw new NoCommentException("You have not recieved any comment till date.....");
		}
		for (Comment comment : comments) {
			RecievedComments recievedComment=new RecievedComments();
			recievedComment.setCommentDateTime(dtf.format(now));
			recievedComment.setCommentFrom(comment.getCommentFrom());
			recievedComment.setMessage(comment.getMessage());
			recievedComments.add(recievedComment);
		}
		return recievedComments;
	}

	public String deleteSentComment(String commentFrom, String commentTo, String message) 
	{
		List<Comment> comments=commentRepository.findByCommentFrom(commentFrom);
		System.out.println(comments);
		Comment toDelete = null ;
		for (Comment comment : comments) {
			System.out.println(comment.getCommentTo() +" " +comment.getMessage());
			if(comment.getCommentTo()==commentTo && comment.getMessage()==message)
			{
				toDelete=comment;
				break;
			}
		}
		
		commentRepository.deleteById(toDelete.getCommentId());
		
		return "Comment of "+message+" from "+commentFrom +" to "+commentTo + " is deleted successfully!";
	}

	public String deleteSentComment(CommentDto commentDto) {
		List<Comment> comments=commentRepository.findByCommentFrom(commentDto.getCommentFrom());
		Comment toDelete = null ;

		for (Comment comment : comments) {
		
			if(comment.getCommentTo().equals(commentDto.getCommentTo()) && comment.getMessage().equals(commentDto.getMessage()))
			{
				toDelete=comment;
				break;
			}
		}
		if(toDelete!=null)
		{
			commentRepository.deleteById(toDelete.getCommentId());
			return "Comment of "+commentDto.getMessage()+" "+commentDto.getCommentFrom() +" "+commentDto.getCommentTo() + " deleted successfully!";

		}
		
		return "comment is not available";

	}

	public String updateComment(UpdateComments updateComments) 
	{
		List<Comment> comments=commentRepository.findByCommentFrom(updateComments.getCommentFrom());
		Comment toUpdate=null;
		for (Comment comment : comments) {
			if(comment.getCommentTo().equals(updateComments.getCommentTo()) && comment.getMessage().equals(updateComments.getMessage())){
				toUpdate=comment;
				toUpdate.setMessage(updateComments.getUpdateMessage());
				toUpdate.setCommentDateTime(dtf.format(now));
				commentRepository.save(toUpdate);
		
				

			}
		}
		
		
		return "Comment of comment id "+toUpdate.getCommentId()+" is updated successfully ";
	}
	
	

}
