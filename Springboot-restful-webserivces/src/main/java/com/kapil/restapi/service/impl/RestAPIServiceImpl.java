package com.kapil.restapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kapil.restapi.dto.UserDTO;
import com.kapil.restapi.entity.User;
import com.kapil.restapi.exception.EmailExistException;
import com.kapil.restapi.exception.ResourceNotFoundException;
import com.kapil.restapi.mapper.AutoUserMapper;
import com.kapil.restapi.mapper.UserMapper;
import com.kapil.restapi.repository.UserRepository;
import com.kapil.restapi.service.RestAPIService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RestAPIServiceImpl implements RestAPIService {
    
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        // UserMapper to convert DTO to JPA Entity
        //User user = UserMapper.mapToUser(userDTO);


        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailExistException("email already exists for user");
        }
        
        //modelMapper to convert DTO to JPA entity
        User user = modelMapper.map(userDTO, User.class);

        //use mapstruct to conver DTO to JPA entity

        //User user = AutoUserMapper.MAPPER.mapToUser(userDTO);

        User savedUser= userRepository.save(user);
        // UserMapper to conver JPA Entity to DTO 
       // UserDTO saveUserDTO = UserMapper.mapToUserDTO(savedUser);

       //modelMapper to convert JPA entity to DTO
       UserDTO saveUserDTO = modelMapper.map(savedUser, UserDTO.class);
        return saveUserDTO;
        

    }

    @Override
    public List<UserDTO> getAllUser() {
       List<User> users = userRepository.findAll();
    //return users.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());

    return users.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    
    // return users.stream().
    //         map((user) -> AutoUserMapper.Mapper.mapToUserDTO(user)).collect(Collectors.toList());
    //return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long Id) {
       
        User user = userRepository.findById(Id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", Id)
        );

        return modelMapper.map(user,UserDTO.class);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User exUser = userRepository.findById(userDTO.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userDTO.getId())
            );

        exUser.setFirstName(userDTO.getFirstName());
        exUser.setLastName(userDTO.getLastName());
        exUser.setEmail(userDTO.getEmail());

        User updatUser = userRepository.save(exUser);

        return modelMapper.map(updatUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User exUser = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id) 
        );

         userRepository.deleteById(id);

       }
}
