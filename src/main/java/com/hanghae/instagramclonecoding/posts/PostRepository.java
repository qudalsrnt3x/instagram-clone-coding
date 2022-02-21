package com.hanghae.instagramclonecoding.posts;

import com.hanghae.instagramclonecoding.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByUser( User user);

}
