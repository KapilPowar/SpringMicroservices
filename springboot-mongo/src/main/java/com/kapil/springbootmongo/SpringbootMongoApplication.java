package com.kapil.springbootmongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.springbootmongo.dao.BookRepository;
import com.kapil.springbootmongo.model.Book;

@SpringBootApplication
@RestController
@RequestMapping("/books")
public class SpringbootMongoApplication {

	@Autowired
    private BookRepository repository;

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return repository.save(book);
        
    }

    @GetMapping()
    public List<Book> getBooks() {
        return repository.findAll();
    }
    

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoApplication.class, args);
	}

}
