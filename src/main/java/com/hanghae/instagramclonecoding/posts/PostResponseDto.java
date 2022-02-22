package com.hanghae.instagramclonecoding.posts;


import com.hanghae.instagramclonecoding.posts.comment.Comment;
import com.hanghae.instagramclonecoding.posts.comment.CommentUserDto;
import com.hanghae.instagramclonecoding.posts.like.Like;
import com.hanghae.instagramclonecoding.posts.like.LikeUserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PostResponseDto {
    private final Long id;
    private final Long uid;
    private final String nickname;
    private final String content;
    private final String imageUrl;
    private Long commentCount;
    private Long likeCount;
    private List<CommentUserDto> commentUserDto;
    private List<LikeUserDto> likeUserDto;

    private final String user_profile;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;



    public PostResponseDto(Long id, Long uid, String nickname, String content, String imageUrl , Long commentCount, Long likeCount,
                           List<CommentUserDto> commentUserDto, List<LikeUserDto> likeUserDto, String user_profile, LocalDateTime createdAt, LocalDateTime modifiedAt
) {
        this.id = id;
        this.uid = uid;
        this.nickname = nickname;
        this.content = content;
        this.imageUrl = imageUrl;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.commentUserDto = commentUserDto;
        this.likeUserDto = likeUserDto;
        this.user_profile = user_profile;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
