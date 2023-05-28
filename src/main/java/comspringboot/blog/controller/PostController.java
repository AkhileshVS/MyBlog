package comspringboot.blog.controller;

import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.paylod.PostResponse;
import comspringboot.blog.service.PostService;
import comspringboot.blog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static comspringboot.blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping
    public PostResponse getPost(
            @RequestParam(value = "pageno",defaultValue = AppConstants.Default_Page_Number,required = false) int pageno,
            @RequestParam(value = "pagesize",defaultValue =AppConstants. Default_Page_Size,required = false) int pagesize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.Default_SortBy ,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.Default_orderBy,required = false) String sortDir
    ){
        return postService.getPost(pageno,pagesize,sortBy,sortDir);

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

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
        postService.deleteById(id);
        return  new ResponseEntity<>("Post Deleted Succesfully!!.",HttpStatus.OK);

    }

}
