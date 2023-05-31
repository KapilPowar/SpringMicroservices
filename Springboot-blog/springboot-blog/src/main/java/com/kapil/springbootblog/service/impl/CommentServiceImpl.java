package com.kapil.springbootblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kapil.springbootblog.entity.Comment;
import com.kapil.springbootblog.entity.Post;
import com.kapil.springbootblog.exception.BlogAPIException;
import com.kapil.springbootblog.exception.ResourceNotFoundeException;
import com.kapil.springbootblog.payload.CommentDto;
import com.kapil.springbootblog.repository.CommentRepository;
import com.kapil.springbootblog.repository.PostRepository;
import com.kapil.springbootblog.service.CommentService;



@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper= mapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
       Comment comment = mapToEntity(commentDto);
    
       //retrieve postby id
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundeException("Post", "id", postId));
        // set post to comment entity
        comment.setPost(post);

        //save comment to DB
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }
    

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //Retrive Comments by Post Id
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());


    }
    
    
    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
         // CommentDto commentDto = new CommentDto();
        // commentDto.setId(comment.getId());
        // commentDto.setName(comment.getName());
        // commentDto.setEmail(comment.getEmail());
        // commentDto.setBody(comment.getBody());
        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        // Comment comment= new Comment();
        // comment.setId(commentDto.getId());
        // comment.setName(commentDto.getName());
        // comment.setEmail(commentDto.getEmail());
        // comment.setBody(commentDto.getBody());

        return comment;
    }


    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //retrieve postby id
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundeException("Post", "id", postId));
        
        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
            () -> new ResourceNotFoundeException("Comment", "id", commentId));
        
        if(!comment.getPost().getId().equals(post.getId())){
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
            }
        return mapToDTO(comment);
    }


    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        //retrieve postby id
            Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundeException("Post", "id", postId));
        
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundeException("Comment", "id", commentId));
            
            if(!comment.getPost().getId().equals(post.getId())){
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
            }
            comment.setName(commentRequest.getName());
            comment.setEmail(commentRequest.getEmail());
            comment.setBody(commentRequest.getBody());

            Comment updatedComment = commentRepository.save(comment);
            return mapToDTO(updatedComment);
    }


    @Override
    public void deleteComment(Long postId, Long commentId) {
        //retrieve postby id
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundeException("Post", "id", postId));
        //retrive comment by commentID
        Comment comment = commentRepository.findById(commentId).orElseThrow(
            () -> new ResourceNotFoundeException("Comment", "id", commentId));
        
        //Check if comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentRepository.delete(comment);  
    }


}
