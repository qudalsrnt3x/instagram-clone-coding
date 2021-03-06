package com.hanghae.instagramclonecoding.posts.like;


import lombok.Getter;

@Getter
public class LikeResponseDto {
    private Long postId;
    private Long likeCount;

    public LikeResponseDto(Long postId, Long likeCount){
        this.postId = postId;
        this.likeCount = likeCount;
    }
}