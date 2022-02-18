package com.hanghae.instagramclonecoding.repository;

import com.hanghae.instagramclonecoding.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
