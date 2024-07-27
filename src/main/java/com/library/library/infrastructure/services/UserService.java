package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.used_request.UserRequest;
import com.library.library.api.dto.response.used_responses.UserResponse;
import com.library.library.domain.entities.UserEntity;
import com.library.library.domain.repositories.UserRepository;
import com.library.library.infrastructure.abstract_services.IUserService;
import com.library.library.infrastructure.helpers.UserMapper;
import com.library.library.utils.enums.SortType;
import com.library.library.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private  final UserMapper userMaper;
    @Autowired
    private  final  UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest request) {
        UserEntity user = this.userMaper.requestToEntity(request);
        return this.userMaper.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pageable = null;


        switch (sortType) {
            case ASC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
            case DESC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
            case NONE -> pageable = PageRequest.of(page, size);
        }

        return this.userRepository.findAll(pageable).map(user -> this.userMaper.entityToResponse(user));
    }

    @Override
    public UserResponse findById(Long id) {
        return  this.userMaper.entityToResponse(this.getById(id));
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = this.getById(id);
        this.userRepository.delete(userEntity);
    }


    private UserEntity getById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The user with the specified id was not found"));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        UserEntity user = this.getById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        UserEntity updatedAuthor = userRepository.save(user);

        return userMaper.entityToResponse(updatedAuthor);
    }
}
