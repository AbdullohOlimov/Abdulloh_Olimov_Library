package com.example.library.service.ImplService;

import com.example.library.entity.Librarian;
import com.example.library.repository.LibrarianRepository;
import com.example.library.service.LibrarianService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LibrarianServiceImpl implements LibrarianService {
    private final LibrarianRepository librarianRepository;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Librarian> librarian = librarianRepository.findByUsername(username);
        if(librarian.isPresent()){
            return librarian.get();
        }else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
