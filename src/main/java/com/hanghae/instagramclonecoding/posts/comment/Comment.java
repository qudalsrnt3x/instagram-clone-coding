package com.hanghae.instagramclonecoding.posts.comment;

import com.hanghae.instagramclonecoding.domain.Timestamped;
import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto requestDto, Post post, User user) {
        this.content = requestDto.getContent();
        this.post = post;
        this.user = user;
    }
}


//    commentList”: [{
//                “commentId”:”댓글id”,
//                “userId”:”유저id”,
//                “nickname”:”닉네임”,
//                “content”:”내용”,
//                “createdAt”:”작성일”
//    }],