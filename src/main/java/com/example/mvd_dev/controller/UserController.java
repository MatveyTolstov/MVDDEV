package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.UserDto;
import com.example.mvd_dev.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        var user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        var user = userService.update(id, userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("user-role/{id}")
    public ResponseEntity<String> getUserRoleById(@PathVariable long id) {
        var userRole = userService.getUserRole(id);

        return ResponseEntity.ok(userRole);
    }
}
