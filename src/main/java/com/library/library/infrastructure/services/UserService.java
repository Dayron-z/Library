package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.custom_request.AuthorUpdateRequest;
import com.library.library.api.dto.request.used_request.AuthorRequest;
import com.library.library.api.dto.response.used_responses.AuthorResponse;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.utils.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IAuthorService {
    @Override
    public AuthorResponse create(AuthorRequest request) {
        return null;
    }

    @Override
    public Page<AuthorResponse> getAll(int page, int size, SortType sortType) {
        return null;
    }

    @Override
    public AuthorResponse findById(Long aLong) {
        return null;
    }

    public AuthorResponse update(AuthorRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public AuthorResponse update(AuthorUpdateRequest request, Long id) {
        return null;
    }
}
