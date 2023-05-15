package comspringboot.blog.service;


import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

    List<Post> getPost();
    Optional<Post> getpostbyId(Long Id);
}
