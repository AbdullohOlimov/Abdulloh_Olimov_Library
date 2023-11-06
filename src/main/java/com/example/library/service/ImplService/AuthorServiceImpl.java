package com.example.library.service.ImplService;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.ResultDto;
import com.example.library.entity.Authors;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResultDto> addAuthor(AuthorDto authors) {
        authorRepository.save(mapper.toEntity(authors));
        return ResponseEntity.ok(new ResultDto(200, "SUCCESS"));
    }

    @Override
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> list = new LinkedList<>();
        List<Authors> all = authorRepository.findAll();
        for (Authors authors : all) {
            list.add(mapper.toDto(authors));
        }return ResponseEntity.ok(list);

    }
}
