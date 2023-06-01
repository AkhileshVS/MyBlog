package comspringboot.blog.service;

import comspringboot.blog.entity.Comment;
import comspringboot.blog.entity.Post;
import comspringboot.blog.exception.BlogApiException;
import comspringboot.blog.exception.ResourceNotFoundException;
import comspringboot.blog.paylod.CommentDto;
import comspringboot.blog.repository.CommentRepository;
import comspringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Override
    public void deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","id",id));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to given post");
        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateCommentByID(Long postId, Long id,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","id",id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to given post");
        }
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment =  commentRepository.save(comment);


        return mapToDTO(updatedComment);
    }

    @Override
    public CommentDto GetCommentsById(Long postId, Long commentId) {
        // Retrieve post by id
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));

       if(!comment.getPost().getId().equals(post.getId())){
           throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to Post");
       }

        return mapToDTO(comment);
    }

    @Override
    public List<CommentDto> GetComments(Long postId) {
        //retrieve comments by id
       List<Comment> comments= commentRepository.findByPostId(postId);
       //convert to list of dto
       return comments.stream().map(comment->mapToDTO(comment)).collect(Collectors.toList());

    }

    private  Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setId(commentDto.getId());
        return comment;
    }
    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = new CommentDto();

        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());

        return commentDto;
    }
}
