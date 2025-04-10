package com.example.mvd_dev.controller;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.modeldto.RoleDto;
import com.example.mvd_dev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Получить все роли
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.allRoles();
        return ResponseEntity.ok(roles);
    }

    // Получить роль по имени
    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String roleName) {
        var role = roleService.findByRoleName(roleName);
        return ResponseEntity.ok(role);
    }

    // Создать новую роль
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role savedRole = roleService.save(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    // Обновить существующую роль
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @RequestBody RoleDto roleDetails) {
        Role updatedRole = roleService.update(roleDetails, id);
        return ResponseEntity.ok(updatedRole);
    }

    // Удалить роль
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}