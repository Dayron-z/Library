package com.library.library.infrastructure.abstract_services;

import com.library.library.api.dto.request.used_request.AuthorRequest;
import com.library.library.api.dto.response.used_responses.AuthorResponse;

public interface IAuthorService extends CrudService <AuthorRequest, AuthorResponse, Long> {
}
