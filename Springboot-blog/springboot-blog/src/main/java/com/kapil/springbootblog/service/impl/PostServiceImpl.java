package com.kapil.springbootblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.kapil.springbootblog.entity.Post;
import com.kapil.springbootblog.exception.ResourceNotFoundeException;
import com.kapil.springbootblog.payload.PostDto;
import com.kapil.springbootblog.payload.PostResponse;

import com.kapil.springbootblog.repository.PostRepository;
import com.kapil.springbootblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;
    
    public PostServiceImpl(PostRepository postRepository , ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        //convert DTO to entity

        Post post = mapToEntity(postDto);
        // Post post = new Post();
        // post.setTitle(postDto.getTitle());
        // post.setDescription(postDto.getDescription());
        // post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //convert entity to DTO 

        PostDto postResponse = mapToDTO(newPost);
        // PostDto postResponse = new PostDto();
        // postResponse.setId(newPost.getId());
        // postResponse.setTitle(newPost.getTitle());
        // postResponse.setDescription(newPost.getDescription());
        // postResponse.setContent(newPost.getContent());
        

        return postResponse;
    }


    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir){
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
        //Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        //get content from page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto>  content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
       
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }
    
    //convert entity to dto
    private PostDto mapToDTO (Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        
        // PostDto postDto = new PostDto();
        // postDto.setId(post.getId());
        // postDto.setTitle(post.getTitle());
        // postDto.setDescription(post.getDescription());
        // postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity (PostDto postDto){
        Post post= mapper.map(postDto, Post.class);
        // Post post = new Post();
        // post.setTitle(postDto.getTitle());
        // post.setDescription(postDto.getDescription());
        // post.setContent(postDto.getContent());

        return post;
    }


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundeException("Post", "id", id));
        return mapToDTO(post);

    }


    @Override
    public PostDto updatePost(PostDto postDto, long id) {
    //get Post By Id from database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundeException("Post", "id", id));

    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    Post udpatedPost = postRepository.save(post);
    return mapToDTO(udpatedPost);
    }


    @Override
    public void deletePostById(long id) {
         //get Post By Id from database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundeException("Post", "id", id));

       postRepository.delete(post);
       
    }
}