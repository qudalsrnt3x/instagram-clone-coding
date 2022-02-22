package com.hanghae.instagramclonecoding.posts;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController

public class PostController {


    private final PostService postService;



    // 게시글 작성
    @PostMapping("/api/post")
    public Post createPost(
            @RequestBody PostRequestDto postRequestDto,
            UserDetailsImpl userDetails
    ) {
        User user = userDetails.getUser();

        return PostService.createPost(postRequestDto, user);
    }

    // 게시글 조회
    @GetMapping("/api/post")
    public List<PostResponseDto> getPost() {
        return postService.getPost();
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
        return postId;
    }
}




