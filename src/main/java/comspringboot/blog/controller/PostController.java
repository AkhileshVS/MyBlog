package comspringboot.blog.controller;

import comspringboot.blog.entity.Post;
import comspringboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity createPost(@RequestBody Post post){

        postService.createPost(post);
        ResponseEntity responseEntity = new ResponseEntity<>( post,HttpStatus.CREATED);
        return responseEntity;

    }

}
