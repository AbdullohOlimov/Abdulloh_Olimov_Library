package com.example.library.repository;

import com.example.library.entity.Books;
import com.example.library.entity.Rental;
import com.example.library.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findByStudentAndBook(Students student, Books book);
}
