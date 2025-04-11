package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.UserDto;
import com.example.mvd_dev.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        var user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        var user = userService.update(id, userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("user/role/{id}")
    public ResponseEntity<String> getUserRoleById(@PathVariable long id) {
        var userRole = userService.getUserRole(id);

        return ResponseEntity.ok(userRole);
    }
}
