package com.kapil.springbootblog.service;

import com.kapil.springbootblog.payload.PostDto;
import com.kapil.springbootblog.payload.PostResponse;


public interface PostService {
    
    PostDto createPost( PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);


    PostDto getPostById (long id);

    PostDto updatePost (PostDto postDto, long id);

    void deletePostById (long id);
}
