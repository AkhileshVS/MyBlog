package comspringboot.blog.service;

import comspringboot.blog.paylod.CommentDto;

public interface CommentService {
    CommentDto CreateComment(Long postId,CommentDto commentDto);
}
