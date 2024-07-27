package com.library.library.api.controllers;

import com.library.library.api.dto.request.custom_request.AuthorUpdateRequest;
import com.library.library.api.dto.request.used_request.AuthorRequest;
import com.library.library.api.dto.response.used_responses.AuthorResponse;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private final IAuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size, @RequestHeader(required = false) SortType sortType){
        //Acá afectamos el comportamiento de sort en case de ser null
        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        Page<AuthorResponse> authorResponses = this.authorService.getAll(page - 1, size, sortType);
        return  ResponseEntity.ok(authorResponses);
    }


    @PostMapping
    public ResponseEntity<AuthorResponse> create(@Validated @RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(this.authorService.create(authorRequest));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.authorService.findById(id));
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<AuthorResponse> update(@RequestBody AuthorUpdateRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.authorService.update(request, id));
    }

}
