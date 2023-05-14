package comspringboot.blog.service;

import comspringboot.blog.entity.Post;
import comspringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImplmentation implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Override
    public void createPost(Post post){
        postRepository.save(post);

    }
}
