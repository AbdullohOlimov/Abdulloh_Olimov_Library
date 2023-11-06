package com.example.library.repository;

import com.example.library.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    Optional<Authors> findByAuthorName(String name);
}
