package com.kapil.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.kapil.restapi.dto.UserDTO;
import com.kapil.restapi.service.RestAPIService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private RestAPIService restAPI;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> userList = restAPI.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserDTO user){
      UserDTO saveUser = restAPI.createUser(user);
      return new ResponseEntity<>(saveUser, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("id") Long userId){

      UserDTO  userDTO = restAPI.getUserById(userId);

      return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, 
                                              @RequestBody UserDTO user){
      user.setId(userId); 
      
      UserDTO udaptUser = restAPI.updateUser(user);

      return new ResponseEntity<UserDTO>(udaptUser, HttpStatus.OK);
      

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        restAPI.deleteUser(id);

        return new ResponseEntity<>("user successfully deleted !!", HttpStatus.OK);



    }
    

}
