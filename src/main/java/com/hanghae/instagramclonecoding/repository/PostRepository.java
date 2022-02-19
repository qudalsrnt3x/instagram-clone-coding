package com.hanghae.instagramclonecoding.repository;

import com.hanghae.instagramclonecoding.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>
{

}
