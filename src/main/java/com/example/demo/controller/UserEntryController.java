package com.example.demo.controller;

import com.example.demo.model.UserEntry;
import com.example.demo.service.UserEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    private final UserEntryService service;

    public UserEntryController(UserEntryService service) {
        this.service = service;
    }

    // POST /user
    @PostMapping
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry entry) {
        try {
            UserEntry saved = service.createUser(entry);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserEntry> getUserById(@PathVariable Long id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /user?type=ADMIN
    @GetMapping
    public ResponseEntity<List<UserEntry>> getUsers(@RequestParam(required = false, name = "type") String typeFilter) {
        return ResponseEntity.ok(service.getAllUsers(typeFilter));
    }
}
