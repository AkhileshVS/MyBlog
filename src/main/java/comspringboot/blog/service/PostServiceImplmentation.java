package comspringboot.blog.service;

import comspringboot.blog.entity.Post;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplmentation implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    public PostServiceImplmentation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    @Override
    public Optional<Post> getpostbyId(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle()) ;
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

     Post post1 =    postRepository.save(post);

    PostDto postDto1 = new PostDto();
    postDto1.setId(post1.getId());
    postDto1.setContent(post1.getContent());
    postDto1.setTitle(post1.getTitle());
    postDto1.setDescription(post1.getDescription());

        return postDto1;
    }
}
