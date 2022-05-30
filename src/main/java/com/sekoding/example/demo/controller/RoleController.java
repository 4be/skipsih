package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.RoleRequest;
import com.sekoding.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllRole() {
        Object data = roleService.getAllRole();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRole(@RequestBody RoleRequest roleRequest) {
        Object data = roleService.createRole(roleRequest);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRole(@PathVariable Integer id) {
        Object data = roleService.getRole(id);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Integer id, @RequestBody RoleRequest roleRequest) {
        Object data = roleService.updateRole(id, roleRequest);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Integer id) {
        Object data = roleService.deleteRole(id);
        return ResponseEntity.ok(data);
    }

}
