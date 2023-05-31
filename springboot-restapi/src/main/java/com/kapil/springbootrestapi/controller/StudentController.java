package com.kapil.springbootrestapi.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.springbootrestapi.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {
    
    @GetMapping("student")
    public ResponseEntity<Student>  getStudent(){
        Student student = new Student(1,"Kapil","Powar");
        //return new ResponseEntity<>(student, HttpStatus.OK );
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
        .header("custom-header","Kapil" )
        .body(student);
    }

    @GetMapping
    public ResponseEntity <List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add( new Student(1,"K1","P1"));
        students.add( new Student(2,"K2","P2"));
        students.add( new Student(3,"K3","P3"));
        students.add( new Student(4,"K4","P4"));
        students.add( new Student(5,"K5","P5"));

        //return new ResponseEntity<>(students,HttpStatus.OK);
        // return ResponseEntity.ok()
        // .header("custom-header","Kapil" )
        // .body(students);
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){

        Student student = new Student(id,"Kapil","Powar");
        return ResponseEntity.ok(student);
        //return new Student(id,"Kapil","Powar");
    }

    @GetMapping("query")
    public ResponseEntity<Student>  studentRequest(@RequestParam int id){
        Student student = new Student(id,"Kapil","Powar");
        return ResponseEntity.ok(student);
        // return new Student(id,"Kapil","Powar");
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student,HttpStatus.CREATED);
        //return student;
    }

    @PutMapping("{id}/update")
    // @ResponseStatus(HttpStatus.upda)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
       
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
        //return student;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("student deleted successfully");
    }
}
