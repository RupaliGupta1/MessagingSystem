package com.comment.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comment.Service.CommentService;
import com.comment.dto.CommentDto;
import com.comment.dto.RecievedComments;
import com.comment.dto.SentComments;
import com.comment.dto.UpdateComments;
import com.comment.exceptions.InValidNameException;
import com.comment.exceptions.NoCommentException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment") 
public class CommentController 
{
	@Autowired 
	private CommentService commentService;
	
	
	@PostMapping("/addComment")
	public ResponseEntity<String> addComment(@Valid @RequestBody CommentDto commentDto) throws InValidNameException  

	{ 
		String commentFrom=commentDto.getCommentFrom();
		String commentTo=commentDto.getCommentTo();
		
		String name=commentFrom+commentTo;
		
		boolean isCharNum=isNameContainAnyCharNum(name);
		
		if(!isCharNum)
		{
			throw new InValidNameException("Invalid");
		}

	  ResponseEntity<String> isAdded=	commentService.addComment(commentDto);
	   System.out.println(isAdded);
		if(HttpStatus.OK != null) {
			return new ResponseEntity<String>("comment added succesfully",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Invalid Request",HttpStatus.BAD_REQUEST);
	}
	
	//sent comment
	@GetMapping("/sent/{commentFrom}")
	public ResponseEntity<List<SentComments>> getSentComments(@PathVariable String commentFrom) throws NoCommentException
	{
		List<SentComments> sentComments=commentService.getSentComments(commentFrom);
		if(sentComments.isEmpty())
		{
			return new ResponseEntity<List<SentComments>>(sentComments,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SentComments>>(sentComments,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/recieved/{commentTo}")
	public ResponseEntity<List<RecievedComments>> getRecievedComments(@PathVariable String commentTo) throws NoCommentException
	{
		if(commentService.getRecievedComments(commentTo)==null)
		{
			return new ResponseEntity<List<RecievedComments>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<RecievedComments>>(commentService.getRecievedComments(commentTo),HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/delete") 
	public ResponseEntity<String> deleteSentComment(@RequestBody CommentDto commentDto)
	{
		return new ResponseEntity<String>(commentService.deleteSentComment(commentDto),HttpStatus.OK);
		
	}
	
	@PutMapping("/edit")
	public ResponseEntity<String> updateComment(@RequestBody UpdateComments updateComments)
	{
       return new ResponseEntity<String>(commentService.updateComment(updateComments),HttpStatus.OK);
	}
	
	
	//check whether the name is valid or not
	
	public boolean isNameContainAnyCharNum(String name)
	{
		String regex="^[a-zA-Z]+$";
		Pattern pattern=Pattern.compile(regex);
		
		Matcher matcher=pattern.matcher(name);
		
		return matcher.matches();
	}
	
	
	
}
