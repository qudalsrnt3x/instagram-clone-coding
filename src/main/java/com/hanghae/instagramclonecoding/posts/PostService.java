package com.hanghae.instagramclonecoding.posts;


import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.posts.comment.Comment;
import com.hanghae.instagramclonecoding.posts.comment.CommentRepository;
import com.hanghae.instagramclonecoding.posts.like.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;


    //글 작성
    @Transactional
    public Post createPost(PostRequestDto postRequestDto, User user) {

        if (postRequestDto.getImageUrl() == null) {
            throw new IllegalArgumentException("이미지를 입력해주세요.");
        }

        String content = postRequestDto.getContent();
        if (postRequestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if (content.length() > 1000) {
            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
        }

        Post post = new Post(postRequestDto, user);

        return postRepository.save(post);
    }



    //전체글 조회
    public List<PostResponseDto> getPost() {

        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            Long commentCount = commentRepository.countByPost(post);
            Long likeCount = likeRepository.countByPost(post);
            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getId(),
                    post.getUser().getId(),
                    post.getUser().getNickname(),
                    post.getContent(),
                    post.getImageUrl(),
                    commentCount,
                    likeCount,
                    post.getCommentList(),
                    post.getLikeList()
            );

            postResponseDtos.add(postResponseDto);

        }
        return postResponseDtos;
    }


    //삭제
    @Transactional
    public Long deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        User user = post.getUser();
        Long deleteId = user.getId();
        if (!Objects.equals(userDetails.getUser().getId(), deleteId)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        for (Comment comment : comments) {
            commentRepository.deleteById(comment.getId());
        }
        likeRepository.deleteByPost(post);
        postRepository.deleteById(postId);
        return postId;
    }


}