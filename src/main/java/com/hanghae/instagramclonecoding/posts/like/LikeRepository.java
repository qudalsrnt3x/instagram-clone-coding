package com.hanghae.instagramclonecoding.posts.like;

import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);

    Long countByPost(Post post);

    Long deleteByPost(Post post);

    List<Like> findAllByPost(Post post);

}
