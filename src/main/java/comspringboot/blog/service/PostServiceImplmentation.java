package comspringboot.blog.service;

import comspringboot.blog.entity.Post;
import comspringboot.blog.exception.ResourceNotFoundException;
import comspringboot.blog.paylod.PostDto;
import comspringboot.blog.paylod.PostResponse;
import comspringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplmentation implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostResponse getPost(int pageno, int pagesize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageno,pagesize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> ListOfPosts = posts.getContent();

        List<PostDto> content = ListOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements((int) posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }


    public PostServiceImplmentation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private PostDto mapToDto(Post post1){
        PostDto postDto1 = new PostDto();
        postDto1.setId(post1.getId());
        postDto1.setContent(post1.getContent());
        postDto1.setTitle(post1.getTitle());
        postDto1.setDescription(post1.getDescription());
        return postDto1;
    }

    @Override
    public PostDto getpostbyId(Long id) {

       Post post =  postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle()) ;
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

     Post post1 =   postRepository.save(post);



        return mapToDto(post1);
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Posst","Id",id));
        postRepository.delete(post);
    }

    @Override
    public PostDto updatepost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

      Post updatepost =  postRepository.save(post);
    return mapToDto(updatepost);
    }
}
