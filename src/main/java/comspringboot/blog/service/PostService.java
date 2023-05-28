package comspringboot.blog.service;


import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.paylod.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getPost(int pageno, int pagesize,String sortBy,String sortDir);
    PostDto getpostbyId(Long Id);

    PostDto updatepost(PostDto postDto, Long id);

    void deleteById(Long id);
}
