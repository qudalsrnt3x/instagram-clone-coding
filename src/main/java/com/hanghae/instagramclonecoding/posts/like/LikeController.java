package com.hanghae.instagramclonecoding.posts.like;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("api/like/{postId}")
    public LikeResponseDto Like(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.addLike(postId, userDetails.getUser().getId());
    }
}
