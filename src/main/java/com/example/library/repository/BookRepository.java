package com.example.library.repository;

import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
    @Query("SELECT b FROM Books b JOIN b.authors a WHERE a.id = :authorId")
    List<Books> findBooksByAuthorId(@Param("authorId") Integer authorId);
}


