package com.example.library.service.ImplService;

import com.example.library.dto.RentRequestDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.entity.Books;
import com.example.library.entity.Rental;
import com.example.library.entity.Students;
import com.example.library.repository.BookRepository;
import com.example.library.repository.RentalRepository;
import com.example.library.repository.StudentRepository;
import com.example.library.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StudentServiceImpl implements StudentService {
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final RentalRepository rentalRepository;

    public StudentServiceImpl(BookRepository bookRepository, StudentRepository studentRepository, RentalRepository rentalRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public boolean rentBook(RentRequestDto dto) {
        Students student = studentRepository.findById(dto.getStudentId()).orElse(null);
        Books book = bookRepository.findById(dto.getBookId()).orElse(null);

        if (student != null && book != null && book.getCount() > 0 && !student.getStatus()) {
            Rental rental = new Rental();
            rental.setStudent(student);
            rental.setBook(book);
            rental.setRentalDate(new Date());
            rental.setDueDate(dto.getDeadline());

            book.setCount(book.getCount() - 1);

            student.setStatus(true);
            Set<Rental> studentRentals = student.getRentals();
            studentRentals.add(rental);
            student.setRentals(studentRentals);

            studentRepository.save(student);
            bookRepository.save(book);
            rentalRepository.save(rental);

            return true;
        }

        return false;
    }
    @Override
    public List<RentResponseDto> getRentalInfoForStudent(Integer studentId) {
            Students student = studentRepository.findById(studentId).orElse(null);

            if (student != null) {
                Set<Rental> studentRentals = student.getRentals();
                List<RentResponseDto> rentalInfoList = new ArrayList<>();

                for (Rental rental : studentRentals) {
                    RentResponseDto rentResponseDto = new RentResponseDto();
                    rentResponseDto.setId(rental.getId());
                    rentResponseDto.setStudentUsername(student.getUsername());
                    rentResponseDto.setBookName(rental.getBook().getBookName());
                    rentResponseDto.setRentedDay(rental.getRentalDate());


                    Date dueDate = rental.getDueDate();
                    Date currentDate = new Date();
                    long timeDifference = dueDate.getTime() - currentDate.getTime();
                    long leftDays = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);
                    rentResponseDto.setLeftDays(leftDays);

                    rentalInfoList.add(rentResponseDto);
                }

                return rentalInfoList;
            }

            return Collections.emptyList();
        }


    }

