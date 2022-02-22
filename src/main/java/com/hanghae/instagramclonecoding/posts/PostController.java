package com.hanghae.instagramclonecoding.posts;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.domain.Response;
import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.image.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final S3Uploader s3Uploader;
    private final PostService postService;


    // 게시글 작성
    @PostMapping("/api/post")
    public Response createPost(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userDetails.getUser();
        postService.createPost(postRequestDto, user);

        Response response = new Response();
        response.setResult(true);
        return response;
    }

    // 게시글 전체조회
    @GetMapping("/api/post")
    public List<PostResponseDto> getPost() {
        return postService.getPost();
    }


    //게시글 삭제
    @DeleteMapping("/api/post/{postId}")
    public Response deletePost(@PathVariable Long postId,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);

        Response response = new Response();
        response.setResult(true);
        return response;
    }


    @PostMapping("/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "static");
        return "test";
    }
}





