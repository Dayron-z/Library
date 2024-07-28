package com.library.library.infrastructure.abstract_services;

import com.library.library.api.dto.request.custom_request.MultipleBookRequest;
import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;

import java.util.List;

public interface IBookService extends CrudService <BookRequest, BookResponse, Long> {
    public BookResponse update(BookRequest request, Long id);
    public List<BookResponse> createBooks(MultipleBookRequest multipleBookRequest);
}
