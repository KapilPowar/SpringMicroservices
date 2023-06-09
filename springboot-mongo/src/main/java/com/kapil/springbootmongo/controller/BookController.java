package com.kapil.springbootmongo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.springbootmongo.dao.BookRepository;

import com.kapil.springbootmongo.model.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
// @RequestMapping("/book")
public class BookController {
    // @Autowired
    // private BookRepository repository;

    // @PostMapping
    // public Book saveBook(@RequestBody Book book){
    //     return repository.save(book);
        
    // }

    // @GetMapping()
    // public List<Book> getBooks() {
    //     return repository.findAll();
    // }
    
    
}
