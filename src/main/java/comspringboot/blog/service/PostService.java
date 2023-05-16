package comspringboot.blog.service;


import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

    List<Post> getPost();
    PostDto getpostbyId(Long Id);

    PostDto updatepost(PostDto postDto, Long id);

    void deleteById(Long id);
}
