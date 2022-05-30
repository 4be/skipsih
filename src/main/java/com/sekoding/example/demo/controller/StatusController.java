package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.StatusRequest;
import com.sekoding.example.demo.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping("/")
    public ResponseEntity<Object> getStatuses() {
        Object data = statusService.getStatuses();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getStatus(@PathVariable Long userId) {
        Object data = statusService.getStatus(userId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/working/{no}")
    public ResponseEntity<Object> getStatusUser(@PathVariable Long no) {
        Object data = statusService.getStatusUser(no);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/result/{no}")
    public ResponseEntity<Object> getSumStatus(@PathVariable Long no) {
        Object data = statusService.getSumUser(no);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createStatus(@RequestBody StatusRequest statusRequest) {
        Object data = statusService.createStatus(statusRequest);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long userId, @RequestBody StatusRequest statusRequest) {
        Object data = statusService.updateStatus(userId, statusRequest);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteStatus() {
        Object data = statusService.deleteStatuses();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteStatus(@PathVariable Long userId) {
        Object data = statusService.deleteStatus(userId);
        return ResponseEntity.ok(data);
    }

}
