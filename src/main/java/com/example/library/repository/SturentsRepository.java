package com.example.library.repository;

import com.example.library.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SturentsRepository extends JpaRepository<Students, Integer> {
}
