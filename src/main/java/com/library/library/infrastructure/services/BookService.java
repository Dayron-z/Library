package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.infrastructure.abstract_services.IBookService;
import com.library.library.utils.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {
    @Override
    public BookResponse create(BookRequest request) {
        return null;
    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sortType) {
        return null;
    }

    @Override
    public BookResponse findById(Long aLong) {
        return null;
    }


    public BookResponse update(BookRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
