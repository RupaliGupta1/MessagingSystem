package com.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comment.Entity.Comment;
import com.comment.Entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {


	List<Comment> findByCommentTo(String commentTo);

	List<Comment> findByPostedBy(User userId);

	List<Comment> findByCommentFrom(String commentFrom);

}
