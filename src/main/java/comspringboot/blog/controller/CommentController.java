package comspringboot.blog.controller;

import comspringboot.blog.paylod.CommentDto;
import comspringboot.blog.service.CommentService;
import comspringboot.blog.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")
            Long postId, @RequestBody  CommentDto commentDto){

        return  new ResponseEntity<>(commentService.CreateComment(postId,commentDto), HttpStatus.CREATED);


    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(value = "postId")Long postId){
        return new ResponseEntity<>(commentService.GetComments(postId),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "postId")Long postId
            ,@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(commentService.GetCommentsById(postId,id),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> UpdateCommentById(@PathVariable(value = "postId")Long postId,
                                                        @PathVariable(value = "id")Long id,
                                                        @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateCommentByID(postId,id,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId")Long postId
            ,@PathVariable(value = "id") Long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Deleted commented succesfully",HttpStatus.OK);
    }
}
