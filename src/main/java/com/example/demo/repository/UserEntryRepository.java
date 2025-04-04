package com.example.demo.repository;

import com.example.demo.model.UserEntry;

import com.example.demo.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Bruker JPA for å lage metoder uten å bruke SQL
//Bruker UserType enum for typesikkerhet

@Repository // Gjør interface tilgjengelig som en Spring-bean
public interface UserEntryRepository extends JpaRepository<UserEntry, Long> {
    List<UserEntry> findByType(UserType type);
}
