package com.hanghae.instagramclonecoding.posts.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequestDto {

    @Size(min = 1,max = 300, message = "댓글은 300자 이하로 입력해 주세요!")
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;

    // 프론트엔드 map 반복문 작업(?) 로직을 위해 사용하지 않는 nickname 변수 추가
    private String nickname;

}
