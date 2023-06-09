package com.kapil.springbootblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.springbootblog.payload.PostDto;
import com.kapil.springbootblog.payload.PostResponse;
import com.kapil.springbootblog.service.PostService;
import com.kapil.springbootblog.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    //create blog Post REST API
    @PostMapping
    public ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //create GetALL rest API
    @GetMapping
    public PostResponse getAllPosts( 
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    //get PostByID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById( @PathVariable(name="id") long id ){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //udpate postbyid REST 
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost (@RequestBody PostDto postDto, @PathVariable(name ="id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Delete post by ID rEST APis
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable (name ="id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity deleted successfully", HttpStatus.OK);
    }
}

