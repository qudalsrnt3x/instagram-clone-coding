package com.hanghae.instagramclonecoding.posts.comment;


import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.posts.Post;
import com.hanghae.instagramclonecoding.posts.PostRepository;
import com.hanghae.instagramclonecoding.posts.PostResponseDto;
import com.hanghae.instagramclonecoding.posts.like.Like;
import com.hanghae.instagramclonecoding.posts.like.LikeUserDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    //댓글 작성
    @Transactional
    public Comment createComment(
            Long postId,
            CommentRequestDto requestDto,
            UserDetailsImpl userDetails,
            BindingResult bindingResult) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
        );
        User user = userDetails.getUser();

        if (bindingResult.hasErrors()){
            throw  new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        } else {
            Comment comment = new Comment(requestDto,post,user);
            return commentRepository.save(comment);
        }
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {
        commentRepository.findById(commentId).orElseThrow(
                        () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                );
        User user = commentRepository.findById(commentId).get().getUser();
        Long commentUserId = user.getId();
        Long loginUserId = userDetails.getUser().getId();
        if (!Objects.equals(commentUserId, loginUserId)){
            throw new IllegalArgumentException("작성자만 삭제 할 수 있습니다.");
        } else {
            commentRepository.deleteById(commentId);
        }
    }

    //댓글 조회
    @ResponseBody
    public List<CommentResponseDto> getComment(Long postId) {
         List<CommentResponseDto> responseDto = new ArrayList<>();
        List<Comment> test = commentRepository.findByPostIdOrderByModifiedAtDesc(postId);
        for (Comment co : test) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(co);
            System.out.println(co.getContent());
            responseDto.add(commentResponseDto);
        }
        return responseDto;
    }
}

