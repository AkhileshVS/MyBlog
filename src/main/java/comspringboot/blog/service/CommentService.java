package comspringboot.blog.service;

import comspringboot.blog.entity.Comment;
import comspringboot.blog.paylod.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto CreateComment(Long postId,CommentDto commentDto);

    List<CommentDto> GetComments(Long postId);

    CommentDto GetCommentsById(Long postId,Long commentId);

    CommentDto updateCommentByID(Long postId, Long id,CommentDto commentDto);

    void deleteComment(Long postId, Long CommentId);

}
