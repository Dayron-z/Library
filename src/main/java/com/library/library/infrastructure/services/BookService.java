package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.custom_request.MultipleBookRequest;
import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;
import com.library.library.domain.entities.Author;
import com.library.library.domain.entities.Book;
import com.library.library.domain.repositories.AuthorRepository;
import com.library.library.domain.repositories.BookRepository;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.infrastructure.abstract_services.IBookService;
import com.library.library.infrastructure.helpers.BookMapper;
import com.library.library.utils.enums.SortType;
import com.library.library.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final BookMapper bookMapper;



    @Override
    public BookResponse create(BookRequest request) {
        Author author = this.authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new BadRequestException("The author with the specified id was not found"));
        Book book = this.bookMapper.requestToEntity(request);
        book.setAuthor(author);
        return this.bookMapper.entityToResponse(this.bookRepository.save(book));
    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pageable = null;


        switch (sortType) {
            case ASC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "title"));
            case DESC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "title"));
            case NONE -> pageable = PageRequest.of(page, size);
        }

        return this.bookRepository.findAll(pageable).map(book -> this.bookMapper.entityToResponse(book));
    }

    @Override
    public BookResponse findById(Long id) {
        BookResponse bookResponse = this.bookMapper.entityToResponse(this.getById(id));
        return bookResponse;
    }

    @Override
    public BookResponse update(BookRequest request, Long id) {
        Book book = this.getById(id);
        Author author = this.authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new BadRequestException("The author with the specified id was not found"));



        book.setTitle(request.getTitle());
        book.setStatus(request.getStatus());
        book.setPublication_date(request.getPublication_date());
        book.setAuthor(author);


        return this.bookMapper.entityToResponse(this.bookRepository.save(book));
    }

    @Override
    public List<BookResponse> createBooks(MultipleBookRequest multipleBookRequest) {
        List<BookRequest> requests = multipleBookRequest.getBookRequestList();
        List<BookResponse> responses = new ArrayList<>();

        for (BookRequest request : requests) {
            Author author = this.authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new BadRequestException("The author with the specified id was not found"));

            Book book = this.bookMapper.requestToEntity(request);
            book.setAuthor(author);
            Book savedBook = this.bookRepository.save(book);
            responses.add(this.bookMapper.entityToResponse(savedBook));
        }

        return responses;
    }

    @Override
    public void delete(Long id) {
        Book book = this.getById(id);
        this.bookRepository.delete(book);
    }


    private Book getById(Long id){
        return this.bookRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The book with the specified id was not found"));
    }
}
