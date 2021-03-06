package com.hanghae.instagramclonecoding.posts.comment;

import com.hanghae.instagramclonecoding.posts.like.Like;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentUserDto {
    private Long commentId;
    private Long userId;
    private String nickname;
    @Size(min = 1, max = 300, message = "댓글은 300자 이하로 입력해 주세요!")
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String profileImageUrl;


    public CommentUserDto(Comment comment) {
        this.commentId = comment.getId();
        this.userId = comment.getUser().getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.profileImageUrl = comment.getUser().getProfileImageUrl();
    }
}



