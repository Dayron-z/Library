package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.custom_request.AuthorUpdateRequest;
import com.library.library.api.dto.request.used_request.AuthorRequest;
import com.library.library.api.dto.response.used_responses.AuthorResponse;
import com.library.library.domain.entities.Author;
import com.library.library.domain.repositories.AuthorRepository;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.infrastructure.helpers.AuthorMapper;
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
public class AuthorService implements IAuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final AuthorMapper authorMapper;


    @Override
    public AuthorResponse create(AuthorRequest request) {
        Author author = authorMapper.requestToEntity(request);
        return this.authorMapper.entityToResponse(this.authorRepository.save(author));
    }

    @Override
    public Page<AuthorResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pageable = null;


        switch (sortType) {
            case ASC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
            case DESC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
            case NONE -> pageable = PageRequest.of(page, size);
        }

        return this.authorRepository.findAll(pageable).map(author -> this.authorMapper.entityToResponse(author));
    }


    @Override
    public void delete(Long id) {
        Author author = getById(id);
        this.authorRepository.delete(author);
    }

    @Override
    public AuthorResponse update(AuthorUpdateRequest request, Long id) {
        Author author = this.getById(id);
        author.setName(request.getName());

        Author updatedAuthor = authorRepository.save(author);

        return authorMapper.entityToResponse(updatedAuthor);
    }

    @Override
    public AuthorResponse findById(Long id){
        return this.authorMapper.entityToResponse(this.getById(id));
    }


    private Author getById(Long id){
        return this.authorRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The user with the specified id was not found"));
    }
}
