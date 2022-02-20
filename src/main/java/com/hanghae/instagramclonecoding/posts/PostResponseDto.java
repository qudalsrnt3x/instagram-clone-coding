package com.hanghae.instagramclonecoding.posts;


import com.hanghae.instagramclonecoding.posts.comment.Comment;
import com.hanghae.instagramclonecoding.posts.like.Like;
import lombok.Getter;
import lombok.Setter;

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

    private final List<Comment> commentList;
    private final List<Like> likeList;

    //필요한것들 아닌가?
//    private final String user_profile;
//    private final LocalDateTime createdAt;
//    private final LocalDateTime modifiedAt;



    public PostResponseDto(Long id, Long uid, String nickname, String content, String imageUrl , Long commentCount, Long likeCount,
                           List<Comment> commentList, List<Like> likeList) {
        this.id = id;
        this.uid = uid;
        this.nickname = nickname;
        this.content = content;
        this.imageUrl = imageUrl;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.commentList = commentList;
        this.likeList = likeList;
    }
}
