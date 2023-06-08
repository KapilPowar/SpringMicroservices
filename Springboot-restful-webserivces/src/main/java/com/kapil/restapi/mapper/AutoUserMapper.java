package com.kapil.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kapil.restapi.dto.UserDTO;
import com.kapil.restapi.entity.User;

@Mapper
public class AutoUserMapper {

    //AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // UserDTO mapToUserDTO(User user);
    // User mapToUser( UserDTO userDTO);
    
}
