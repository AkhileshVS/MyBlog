package comspringboot.blog.controller;

import comspringboot.blog.paylod.CommentDto;
import comspringboot.blog.service.CommentService;
import comspringboot.blog.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
