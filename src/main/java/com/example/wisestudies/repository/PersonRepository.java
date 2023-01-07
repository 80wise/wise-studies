package com.example.wisestudies.repository;

import com.example.wisestudies.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Override
    Optional<Person> findById(Long id);

    Optional<Person> findByEmail(String email);

    @Query(value = "SELECT password FROM person p WHERE p.id = 0", nativeQuery = true)
    String findSomethingWithCustomizedRequest(int id);
}
