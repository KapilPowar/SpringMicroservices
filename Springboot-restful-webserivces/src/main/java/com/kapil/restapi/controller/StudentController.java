package com.kapil.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.restapi.bean.Student;


@RestController
@RequestMapping("/api")
public class StudentController {
    
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
        
    }

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){

        Student student = new Student(1, "Kapil", "Powar") ;

        return ResponseEntity.ok().body(student);

    }

    @GetMapping("/allstudents")
    public ResponseEntity<List<Student>>getAllStudents(){

        
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kapil","Powar"));
        students.add(new Student(2, "K2","P2"));
        students.add(new Student(3, "K3","P3"));
        students.add(new Student(4, "K4","P4"));
        students.add(new Student(5, "K5","P5"));

       // return ResponseEntity.ok(students);
        return ResponseEntity.ok()
        .header("custom-header","Kapil" )
        .body(students);
       // return new ResponseEntity<>(students,HttpStatus.OK);

    }

    @GetMapping("/student/{ID}")
    public ResponseEntity<Student> getStudentByID( @PathVariable("ID") int id){

        Student student = new Student(id, "Kapil", "Powar") ;

        return ResponseEntity.ok().body(student);

    }

    @GetMapping("/query")
    public ResponseEntity<Student> getStudentByRequest( @RequestParam int id){

        Student student = new Student(id, "Kapil", "Powar") ;

        return ResponseEntity.ok().body(student);

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

       // Student student = new Student(id, "Kapil", "Powar") ;
       System.out.println(student.getId());
       System.out.println(student.getFName());
       System.out.println(student.getLName());
        //return ResponseEntity.ok().body(student);
        return new ResponseEntity<>(student , HttpStatus.CREATED);

    }
}
