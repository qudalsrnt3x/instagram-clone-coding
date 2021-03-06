package com.hanghae.instagramclonecoding.posts.like;

import com.hanghae.instagramclonecoding.User.User;
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
@Table(name = "like_table")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Like(LikeRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.post = requestDto.getPost();
    }
}



//            “likeList”: [{
//              “userId”:”좋아요 한 유저id”