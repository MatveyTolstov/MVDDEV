package com.example.mvd_dev.service;

import com.example.mvd_dev.exception.UserAlreadyExistsException;
import com.example.mvd_dev.exception.UserNotFoundException;
import com.example.mvd_dev.mapper.UserMapper;
import com.example.mvd_dev.modeldto.UserDto;
import com.example.mvd_dev.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private final String USER_ALREADY_EXCEPTION = "Такой логин уже есть!";

    public UserDto save(UserDto userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXCEPTION);
        }

        if (userRepository.existsByNumber(userDto.getNumber())) {
            throw new UserAlreadyExistsException("Такой номер телефона уже есть");
        }

        var user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(USER_ALREADY_EXCEPTION));
    }

    public UserDto update(long id, UserDto userDto) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_ALREADY_EXCEPTION));

        userCurrent.setRoleId(userDto.getRoleId());
        userCurrent.setLogin(userDto.getLogin());
        userCurrent.setPassword(userDto.getPassword());
        userCurrent.setNumber(userDto.getNumber());

        return userMapper.toDto(userRepository.save(userCurrent));
    }

    public void delete(long id) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_ALREADY_EXCEPTION));

        userRepository.delete(userCurrent);
    }

    public UserDetails existByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с именем \"" + login + "\" не найден"));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public String getUserRole(long id) {
        return userRepository.getUserRoleNameById(id)
                .orElseThrow(() -> new UserNotFoundException("Такого пользователя нету"));
    }

}
