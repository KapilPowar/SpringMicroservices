package com.kapil.springbootmongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kapil.springbootmongo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{
        
}
