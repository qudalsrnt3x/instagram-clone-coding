package com.hanghae.instagramclonecoding.posts.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponseDto {
    private String nickname;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
    }
}
