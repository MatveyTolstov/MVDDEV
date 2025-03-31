package com.example.mvd_dev.controller;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.repository.RoleCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleCrudRepository roleCrudRepository;

    @Autowired
    public RoleController(RoleCrudRepository roleCrudRepository) {
        this.roleCrudRepository = roleCrudRepository;
    }

    // Получить все роли
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleCrudRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Получить роль по имени
    @GetMapping("/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String roleName) {
        Role role = roleCrudRepository.findByRoleName(roleName);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Создать новую роль
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleCrudRepository.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    // Обновить существующую роль
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @RequestBody Role roleDetails) {
        Role role = roleCrudRepository.findById(id).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        role.setRoleName(roleDetails.getRoleName());
        Role updatedRole = roleCrudRepository.save(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    // Удалить роль
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
        if (!roleCrudRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleCrudRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}