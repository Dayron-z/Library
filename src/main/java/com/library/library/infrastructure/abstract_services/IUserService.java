package com.library.library.infrastructure.abstract_services;

import com.library.library.api.dto.request.used_request.UserRequest;
import com.library.library.api.dto.response.used_responses.UserResponse;

public interface IUserService extends CrudService <UserRequest, UserResponse, Long> {
}