package com.hanghae.instagramclonecoding.posts.like;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeUserDto {
    private Long userId;

    public LikeUserDto(Like like) {
        this.userId = like.getUser().getId();
    }
}
