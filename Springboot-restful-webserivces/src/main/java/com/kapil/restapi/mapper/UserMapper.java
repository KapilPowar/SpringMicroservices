package com.kapil.restapi.mapper;

import com.kapil.restapi.dto.UserDTO;
import com.kapil.restapi.entity.User;

public class UserMapper {
    
    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO= new UserDTO(
            user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO){
        User user = new User(
            userDTO.getId(),userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        return user;
    }

}
