package comspringboot.blog.controller;

import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping
    public List<Post> getPost(){
        return postService.getPost();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostByid(@PathVariable(name="id") Long id){
        //return new ResponseEntity<>(,HttpStatus.OK);
        return ResponseEntity.ok(postService.getpostbyId(id));
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto){


        ResponseEntity responseEntity = new ResponseEntity<>( postService.createPost(postdto),HttpStatus.CREATED);
        return responseEntity;

    }

}
