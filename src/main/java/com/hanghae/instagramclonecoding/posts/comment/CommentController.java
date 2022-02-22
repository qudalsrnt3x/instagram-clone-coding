package com.hanghae.instagramclonecoding.posts.comment;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import com.hanghae.instagramclonecoding.domain.Response;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 댓글 작성.
    @PostMapping("/api/comment/{postId}")
    public Response createComment(
            @PathVariable Long postId,
            @Validated @RequestBody CommentRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createComment(postId,requestDto,userDetails,bindingResult);

        Response response = new Response();
        response.setResult(true);
        return response;
    }


    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public Response deleteComment(@PathVariable Long commentId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails);
        Response response = new Response();
        response.setResult(true);
        return response;
    }
}
