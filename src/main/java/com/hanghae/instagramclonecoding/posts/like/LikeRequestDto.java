package com.hanghae.instagramclonecoding.posts.like;

import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LikeRequestDto {
    private User user;
    private Post post;
}