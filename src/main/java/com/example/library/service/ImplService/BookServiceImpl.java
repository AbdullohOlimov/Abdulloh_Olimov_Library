package com.example.library.service.ImplService;

import com.example.library.dto.BookReqDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.dto.ResultDto;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import com.example.library.entity.Rental;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.RentalRepository;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper mapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RentalRepository rentalRepository;

    public BookServiceImpl(BookMapper mapper, BookRepository bookRepository, AuthorRepository authorRepository, RentalRepository rentalRepository) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<ResultDto> addBook(BookReqDto bookReqDto) throws Exception {
        List<Authors> authors = authorRepository.findAllById(bookReqDto.getAuthorId());

        if (authors.isEmpty()) {
            throw new Exception("Author not found. Enter author's info first!");
        }

        Set<Authors> authorSet = new HashSet<>(authors);

        Books books = mapper.toEntity(bookReqDto, authorSet);
        bookRepository.save(books);

        return ResponseEntity.ok(new ResultDto(200, "SUCCESS"));
    }

    @Override
    public List<BookResDto> getAllBooks() {
        List<Books> allBooks = bookRepository.findAll();
        List<BookResDto> bookResDtos = new LinkedList<>();
        for (int i = 0; i < allBooks.size(); i++) {
            BookResDto dto = mapper.toDto(allBooks.get(i));
            System.out.println(dto);
            bookResDtos.add(dto);
        }
        return bookResDtos;
    }

    @Override
    public ResponseEntity<ResultDto> delete(Integer id) {
        Optional<Books> books = bookRepository.findById(id);
        if(books.isPresent()){
            bookRepository.delete(books.get());
            return ResponseEntity.ok(new ResultDto(200, "SUCCESS"));
        }else {
            return ResponseEntity.ok(new ResultDto(404, "NOT FOUND"));
        }

    }

    @Override
    public ResponseEntity<BookResDto> editBook(BookReqDto bookReqDto) throws Exception {
        Optional<Books> book = bookRepository.findById(bookReqDto.getId());
        if(book.isPresent()){
            List<Integer> authorsId = bookReqDto.getAuthorId();
            Set<Authors> set = new HashSet<>();
            for (Integer integer : authorsId) {
                Optional<Authors> authors = authorRepository.findById(integer);
                authors.ifPresent(set::add);
            }
            Books books1 = mapper.toEntity(bookReqDto, set);
            bookRepository.save(books1);
            BookResDto dto = mapper.toDto(books1);
            return ResponseEntity.ok(dto);
        }throw new Exception("Book not found!");
    }

    @Override
    public List<BookResDto> getAllByAuthors(Integer id){
        List<BookResDto> list = new LinkedList<>();
        List<Books> books = bookRepository.findBooksByAuthorId(id);
        for (Books book : books) {
            BookResDto dto = mapper.toDto(book);
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<RentResponseDto> getRentalInfoForLibrarian() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentResponseDto> rentalInfoList = new ArrayList<>();

        for (Rental rental : rentals) {
            RentResponseDto librarianRentResponseDto = new RentResponseDto();
            librarianRentResponseDto.setId(rental.getId());
            librarianRentResponseDto.setStudentUsername(rental.getStudent().getUsername());
            librarianRentResponseDto.setBookName(rental.getBook().getBookName());
            librarianRentResponseDto.setRentedDay(rental.getRentalDate());
            Date dueDate = rental.getDueDate();
            Date currentDate = new Date();
            long timeDifference = dueDate.getTime() - currentDate.getTime();
            long leftDays = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);
            librarianRentResponseDto.setLeftDays(leftDays);

            rentalInfoList.add(librarianRentResponseDto);
        }

        return rentalInfoList;
    }
}
