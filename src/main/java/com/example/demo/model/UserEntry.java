package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_entry")
public class UserEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    public UserEntry() {}

    public UserEntry(String email, UserType type) {
        this.email = email;
        this.type = type;
    }

    // Getters og setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }
}
