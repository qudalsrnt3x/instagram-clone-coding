package com.hanghae.instagramclonecoding.posts.like;


import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.posts.Post;
import com.hanghae.instagramclonecoding.posts.PostRepository;
import com.hanghae.instagramclonecoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public LikeResponseDto addLike(Long postId, Long uid) {
        User user = userRepository.findById(uid).orElseThrow(
                () -> new IllegalArgumentException("유저 정보가 없습니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("일기가 없습니다.")
        );

        Like findLike = likeRepository.findByUserAndPost(user,post).orElse(null);

        if(findLike == null){
            LikeRequestDto requestDto = new LikeRequestDto(user, post);
            Like like = new Like(requestDto);
            likeRepository.save(like);
        } else {
            likeRepository.deleteById(findLike.getId());
        }
        return new LikeResponseDto(postId, likeRepository.countByPost(post));
    }
}

