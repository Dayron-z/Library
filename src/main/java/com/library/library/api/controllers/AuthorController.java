package com.library.library.api.controllers;

import com.library.library.api.dto.request.custom_request.AuthorUpdateRequest;
import com.library.library.api.dto.request.used_request.AuthorRequest;
import com.library.library.api.dto.response.used_responses.AuthorResponse;
import com.library.library.infrastructure.abstract_services.IAuthorService;
import com.library.library.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "Authors", description = "Operations related to Authors")
@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final IAuthorService authorService;

    @Operation(summary = "Get all authors", description = "Retrieve a paginated list of authors with optional sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of authors",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAll(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "8") int size,
                                                       @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        Page<AuthorResponse> authorResponses = this.authorService.getAll(page - 1, size, sortType);
        return ResponseEntity.ok(authorResponses);
    }

    @Operation(summary = "Create a new author", description = "Create a new author with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created author",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid author details", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthorResponse> create(@Validated @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(this.authorService.create(authorRequest));
    }

    @Operation(summary = "Get author by ID", description = "Retrieve details of a specific author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved author details",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.authorService.findById(id));
    }

    @Operation(summary = "Update an author", description = "Update details of an existing author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated author",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid update details", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PatchMapping(path = "/{id}")
    public ResponseEntity<AuthorResponse> update(@Validated @RequestBody AuthorUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.authorService.update(request, id));
    }
}
