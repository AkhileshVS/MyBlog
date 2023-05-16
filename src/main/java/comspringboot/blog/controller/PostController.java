package comspringboot.blog.controller;

import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PostDto> getPostByid(@PathVariable(name="id") Long id){
        //return new ResponseEntity<>(,HttpStatus.OK);
        return ResponseEntity.ok(postService.getpostbyId(id));
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto){


        ResponseEntity responseEntity = new ResponseEntity<>( postService.createPost(postdto),HttpStatus.CREATED);
        return responseEntity;

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(value = "id") Long id){
        return  new ResponseEntity<>(postService.updatepost(postDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
        postService.deleteById(id);
        return  new ResponseEntity<>("Post Deleted Succesfully!!.",HttpStatus.OK);

    }

}
