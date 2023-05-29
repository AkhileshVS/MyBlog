package comspringboot.blog.service;

import comspringboot.blog.entity.Comment;
import comspringboot.blog.entity.Post;
import comspringboot.blog.exception.ResourceNotFoundException;
import comspringboot.blog.paylod.CommentDto;
import comspringboot.blog.repository.CommentRepository;
import comspringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override

    public CommentDto CreateComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);
        //retrieve post entity by id
       Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
       //set post to comment entitiy
        comment.setPost(post);

        //comment to db
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);


    }
    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = new CommentDto();

        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());

        return commentDto;
    }

    private  Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setId(commentDto.getId());
        return comment;
    }
}
