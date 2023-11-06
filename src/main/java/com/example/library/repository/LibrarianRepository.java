package com.example.library.repository;

import com.example.library.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Optional<Librarian> findByUsername(String username);
}

