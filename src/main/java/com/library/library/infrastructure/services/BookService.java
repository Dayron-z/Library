package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.infrastructure.abstract_services.IBookService;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {
    @Override
    public BookResponse create(BookRequest request) {
        return null;
    }

    @Override
    public BookResponse getAll() {
        return null;
    }

    @Override
    public BookResponse update(BookRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
