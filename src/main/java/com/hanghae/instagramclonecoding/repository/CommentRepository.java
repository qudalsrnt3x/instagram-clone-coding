package com.hanghae.instagramclonecoding.repository;

import com.hanghae.instagramclonecoding.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
