package com.hanghae.instagramclonecoding.posts.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 댓글 작성.
    @PostMapping("/api/comment/{postId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long postId,
            @Validated @RequestBody CommentRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetails

    ){
        Comment comment = commentService.createComment(postId,requestDto,userDetails,bindingResult);
        return ResponseEntity.ok(comment);
    }


    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        commentService.deleteComment(commentId,userDetails);
    }


}
