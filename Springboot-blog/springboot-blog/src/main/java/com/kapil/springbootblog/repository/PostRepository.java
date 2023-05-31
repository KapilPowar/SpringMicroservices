package com.kapil.springbootblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapil.springbootblog.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
    
}
