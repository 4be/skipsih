package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.UserRequest;
import com.sekoding.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        Object data = userService.getAllUser();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        Object data = userService.createUser(userRequest);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile fileRequest) {
        Object data = userService.createUserByUpload(fileRequest);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Object data = userService.getUserById(id);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {
        Object data = userService.getUserById(id);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/manager/{nik}")
    public ResponseEntity<Object> getUserByNikManager(@PathVariable String nik) {
        Object data = userService.getUserByNikManager(nik);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{nik}")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest userRequest, @PathVariable String nik) {
        Object data = userService.updateUserByNik(userRequest, nik);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Object data = userService.deleteUser(id);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/nik/{nik}")
    public ResponseEntity<Object> deleteUserBYNik(@PathVariable String nik) {
        Object data = userService.deleteUserByNik(nik);
        return ResponseEntity.ok(data);
    }

}
