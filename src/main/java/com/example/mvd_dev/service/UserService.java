package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.UserMapper;
import com.example.mvd_dev.modeldto.UserDto;
import com.example.mvd_dev.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.example.mvd_dev.service.ErrorMessages.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto save(UserDto userDto) {

        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new RuntimeException(LOGIN_ALREADY_EXISTS);
        }

        if (userRepository.existsByNumber(userDto.getNumber())) {
            throw new RuntimeException(PHONE_NUMBER_ALREADY_EXISTS);
        }

        if (userDto != null) {
            throw new RuntimeException(USER_CANNOT_BE_NULL);
        }

        var user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userMapper.toDto(userRepository.findById(id).get());
        }

        throw new RuntimeException(USER_NOT_FOUND);
    }

    public UserDto update(long id, UserDto userDto) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(USER_NOT_FOUND));

        userCurrent.setRoleId(userDto.getRoleId());
        userCurrent.setLogin(userDto.getLogin());
        userCurrent.setPassword(userDto.getPassword());
        userCurrent.setNumber(userDto.getNumber());

        return userMapper.toDto(userRepository.save(userCurrent));

    }

    public void delete(long id) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(USER_NOT_FOUND));

        userRepository.delete(userCurrent);
    }

    public UserDetails existByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NullPointerException("Пользователь с именем \" + login + \" не найден"));

    }


}
