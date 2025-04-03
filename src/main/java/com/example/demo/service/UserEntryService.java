package com.example.demo.service;

import com.example.demo.model.UserEntry;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserEntryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserEntryService {

    private final UserEntryRepository repository;

    public UserEntryService(UserEntryRepository repository) {
        this.repository = repository;
    }

    public UserEntry createUser(UserEntry entry) {
        return repository.save(entry);
    }

    public Optional<UserEntry> getUserById(Long id) {
        return repository.findById(id);
    }

    public List<UserEntry> getAllUsers(String typeFilter) {
        if (typeFilter != null && !typeFilter.isEmpty()) {
            try {
                UserType type = UserType.valueOf(typeFilter.toUpperCase());
                return repository.findByType(type);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid user type: " + typeFilter);
            }
        }
        return repository.findAll();
    }
    
}
