package com.library.library.api.controllers;

import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;
import com.library.library.infrastructure.abstract_services.IBookService;
import com.library.library.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "Books", description = "Operations related to Books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @Operation(summary = "Get all books", description = "Retrieve a paginated list of books with optional sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "8") int size,
                                                     @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        Page<BookResponse> bookResponses = this.bookService.getAll(page - 1, size, sortType);
        return ResponseEntity.ok(bookResponses);
    }

    @Operation(summary = "Create a new book", description = "Create a new book with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created book",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid book details", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BookResponse> create(@Validated @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(this.bookService.create(bookRequest));
    }

    @Operation(summary = "Get book by ID", description = "Retrieve details of a specific book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book details",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<BookResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.findById(id));
    }

    @Operation(summary = "Update an existing book", description = "Update details of an existing book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated book",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid update details", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PatchMapping(path = "/{id}")
    public ResponseEntity<BookResponse> update(@Validated @RequestBody BookRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.update(request, id));
    }

    @Operation(summary = "Delete a book by ID", description = "Delete a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted book", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
