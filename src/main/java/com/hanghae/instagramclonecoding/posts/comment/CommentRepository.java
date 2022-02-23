package com.hanghae.instagramclonecoding.posts.comment;

import com.hanghae.instagramclonecoding.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findAllByPost(Post post);
    List<Comment> findByPostIdOrderByModifiedAtDesc(Long postId);

    Long countByPost(Post post);
}
