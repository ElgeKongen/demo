package com.example.demo.service;

import com.example.demo.model.UserEntry;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserEntryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service // Gjør denne klassen til en Spring-bean som kan injiseres i controlleren
public class UserEntryService {

    private final UserEntryRepository repository;

    // Dependency injection av repository via constructor – gjør testing og struktur enklere
    public UserEntryService(UserEntryRepository repository) {
        this.repository = repository;
    }

    // Oppretter ny bruker – Spring Data JPA tar hånd om SQL gjennom save()
    public UserEntry createUser(UserEntry entry) {
        return repository.save(entry);
    }

    // Henter én bruker basert på ID – bruker Optional for å unngå null
    public Optional<UserEntry> getUserById(Long id) {
        return repository.findById(id);
    }

    // Henter alle brukere – med valgfri filtrering på type
    public List<UserEntry> getAllUsers(String typeFilter) {
        if (typeFilter != null && !typeFilter.isEmpty()) {
            try {
                // Validerer og konverterer string til enum
                UserType type = UserType.valueOf(typeFilter.toUpperCase());
                return repository.findByType(type);
            } catch (IllegalArgumentException e) {
                // Returnerer 500-feil hvis filteret ikke er gyldig – kunne vært forbedret til 400
                throw new RuntimeException("Invalid user type: " + typeFilter);
            }
        }
        // Hvis ingen filter, returner alle
        return repository.findAll();
    }

}
