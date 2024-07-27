package com.library.library.infrastructure.helpers;

import com.library.library.api.dto.request.used_request.UserRequest;
import com.library.library.api.dto.response.used_responses.UserResponse;
import com.library.library.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserHelper {
    UserEntity requestToEntity(UserRequest userRequest);
    UserResponse entityToResponse(UserEntity user);
}

