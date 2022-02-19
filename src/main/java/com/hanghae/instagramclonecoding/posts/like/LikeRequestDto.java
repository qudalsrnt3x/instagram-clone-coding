package com.hanghae.instagramclonecoding.posts.like;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.user.User;
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