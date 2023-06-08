package com.kapil.restapi.service;

import java.util.List;

import com.kapil.restapi.dto.UserDTO;
//import com.kapil.restapi.entity.User;

public interface RestAPIService {
    
     UserDTO createUser(UserDTO user);
     List<UserDTO> getAllUser ();

     UserDTO getUserById(Long Id);

     UserDTO updateUser( UserDTO userDTO);

     void deleteUser(Long id);
}
