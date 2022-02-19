package com.hanghae.instagramclonecoding.posts.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService LikeService;

    @PostMapping("api/like/{postId}")
    public LikeResponseDto Like(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return LikeService.addLike(postId, userDetails.getUser().getId());
    }
}
