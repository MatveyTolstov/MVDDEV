package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.UserEntity;
import com.example.mvd_dev.modeldto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setNumber(userDto.getNumber());
        user.setPassword(userDto.getPassword());
        user.setRoleId(userDto.getRoleId());
        return user;
    }

    public UserDto toDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setNumber(user.getNumber());
        userDto.setPassword(user.getPassword());
         userDto.setRoleId(user.getRoleId());
        return userDto;
    }
}