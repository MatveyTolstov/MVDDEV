package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.UserMapper;
import com.example.mvd_dev.modeldto.UserDto;
import com.example.mvd_dev.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto save(UserDto userDto) {

        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new RuntimeException("Такой логин уже есть!");
        }

        if (userRepository.existsByNumber(userDto.getNumber())) {
            throw new RuntimeException("Такой номер телефона уже есть");
        }

        if (userDto != null) {
            throw new RuntimeException("Пользователь не может быть пустым!");
        }

        var user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userMapper.toDto(userRepository.findById(id).get());
        }

        throw new RuntimeException("Такого пользователя нету");
    }

    public UserDto update(long id, UserDto userDto) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Такого пользователя нету"));

        userCurrent.setRoleId(userDto.getRoleId());
        userCurrent.setLogin(userDto.getLogin());
        userCurrent.setPassword(userDto.getPassword());
        userCurrent.setNumber(userDto.getNumber());

        return userMapper.toDto(userRepository.save(userCurrent));

    }

    public void delete(long id) {
        var userCurrent = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Такого пользователя нету"));

        userRepository.delete(userCurrent);
    }

    public UserDetails existByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NullPointerException("Пользователь с именем \" + login + \" не найден"));

    }


}
