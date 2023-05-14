package comspringboot.blog.service;

import comspringboot.blog.entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    void createPost(Post post);
}
