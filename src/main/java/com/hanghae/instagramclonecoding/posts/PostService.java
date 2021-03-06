package com.hanghae.instagramclonecoding.posts;


import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.posts.comment.Comment;
import com.hanghae.instagramclonecoding.posts.comment.CommentRepository;
import com.hanghae.instagramclonecoding.posts.comment.CommentUserDto;
import com.hanghae.instagramclonecoding.posts.like.Like;
import com.hanghae.instagramclonecoding.posts.like.LikeRepository;
import com.hanghae.instagramclonecoding.posts.like.LikeUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
//        List<LikeUserDto> likeUserDtos = new ArrayList<>();
//        List<CommentUserDto> commentUserDtos = new ArrayList<>();

        for (Post post : posts)
        {

            List<CommentUserDto> commentUserDtos = new ArrayList<>();
            List<LikeUserDto> likeUserDtos = new ArrayList<>();

            Long commentCount = commentRepository.countByPost(post);
            Long likeCount = likeRepository.countByPost(post);

            List<Like> likes = likeRepository.findAllByPost(post);
            List<Comment> comments = commentRepository.findAllByPost(post);


            for (Like like : likes)
            {
                LikeUserDto likeUserDto = new LikeUserDto(like);
                likeUserDtos.add(likeUserDto);
            }

            for (Comment comment : comments) {
                CommentUserDto commentUserDto = new CommentUserDto(comment);
                commentUserDtos.add(commentUserDto);
            }


            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getId(),
                    post.getUser().getId(),
                    post.getUser().getNickname(),
                    post.getContent(),
                    post.getImageUrl(),
                    commentCount,
                    likeCount,
                    commentUserDtos,
                    likeUserDtos,
                    post.getUser().getProfileImageUrl(),
                    post.getCreatedAt(),
                    post.getModifiedAt()
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
        List<Comment> comments = commentRepository.findAllByPost(post);
        for (Comment comment : comments) {
            commentRepository.deleteById(comment.getId());
        }
        likeRepository.deleteByPost(post);
        postRepository.deleteById(postId);
        return postId;
    }

    @ResponseBody
    public List<PostResponseDto> getMyPost(UserDetailsImpl userDetails, Long userId)
    {
        List<Post> postList = postRepository.findAllByUserOrderByCreatedAtDesc(userDetails.getUser());
        List<PostResponseDto> response = new ArrayList<>();

        for (Post post : postList)
        {
            List<CommentUserDto> commentUserDtos = new ArrayList<>();
            List<LikeUserDto> likeUserDtos = new ArrayList<>();

            Long commentCount = commentRepository.countByPost(post);
            Long likeCount = likeRepository.countByPost(post);

            List<Like> likes = likeRepository.findAllByPost(post);
            List<Comment> comments = commentRepository.findAllByPost(post);

            for (Like like : likes)
            {
                LikeUserDto likeUserDto = new LikeUserDto(like);
                likeUserDtos.add(likeUserDto);
            }

            for (Comment comment : comments) {
                CommentUserDto commentUserDto = new CommentUserDto(comment);
                commentUserDtos.add(commentUserDto);
            }


            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getId(),
                    post.getUser().getId(),
                    post.getUser().getNickname(),
                    post.getContent(),
                    post.getImageUrl(),
                    commentCount,
                    likeCount,
                    commentUserDtos,
                    likeUserDtos,
                    post.getUser().getProfileImageUrl(),
                    post.getCreatedAt(),
                    post.getModifiedAt()
            );


            response.add(postResponseDto);

        }
        return response;


    }
}