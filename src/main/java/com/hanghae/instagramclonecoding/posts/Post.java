package com.hanghae.instagramclonecoding.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanghae.instagramclonecoding.domain.Timestamped;
import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.posts.comment.Comment;
import com.hanghae.instagramclonecoding.posts.like.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //
    @Column(columnDefinition = "mediumblob")
    private String imageUrl;

    // 댓글
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnoreProperties({"post"})
    private List<Comment> commentList;

    // 좋아요
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnoreProperties({"post"})
    private List<Like> likeList;


    @Builder
    public Post(PostRequestDto requestDto, User user) {
        this.user = user;
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();

    }
}

//    public Diary(DiaryRequestDto requestDto, User user, List<ImageUrl> imageUrlList1, DiaryLike diaryLike){
//        this.user= user;
//        this.emotion = requestDto.getEmotion();
//        this.tag = requestDto.getTag();
//        this.imageUrlList = imageUrlList1;
//        this.title = requestDto.getTitle();
//        this.content = requestDto.getContent();
//        this.is_open = requestDto.getIs_open();
//        this.diaryLike = diaryLike;
//    }
//
//
//
//    public void updateDiary(DiaryRequestDto requestDto) {
//        this.emotion = requestDto.getEmotion();
//        this.tag = requestDto.getTag();
//        this.imageUrlList = requestDto.getImageUrlList();
//        this.title = requestDto.getTitle();
//        this.content = requestDto.getContent();
//        this.is_open = requestDto.getIs_open();
//    }