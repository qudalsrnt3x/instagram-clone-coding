package com.hanghae.instagramclonecoding.posts.like;


import lombok.Getter;

@Getter
public class LikeResponseDto {
    private Long diaryId;
    private Long totalLike;

    public LikeResponseDto(Long diaryId, Long totalLike){
        this.diaryId = diaryId;
        this.totalLike = totalLike;
    }
}