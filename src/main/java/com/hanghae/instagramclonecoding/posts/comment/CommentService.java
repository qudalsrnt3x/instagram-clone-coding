package com.hanghae.instagramclonecoding.posts.comment;


import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.posts.Post;
import com.hanghae.instagramclonecoding.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
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
}
