package com.hanghae.instagramclonecoding.repository;

import com.hanghae.instagramclonecoding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
