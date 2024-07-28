package com.library.library.api.controllers;

import com.library.library.api.dto.request.used_request.LoanRequest;
import com.library.library.api.dto.response.used_responses.LoanResponse;
import com.library.library.infrastructure.abstract_services.ILoanService;
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

@Tag(name = "Loans", description = "Operations related to Loans")
@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @Operation(summary = "Get all loans", description = "Retrieve a paginated list of loans with optional sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of loans",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "8") int size,
                                                     @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        Page<LoanResponse> loanResponses = this.loanService.getAll(page - 1, size, sortType);
        return ResponseEntity.ok(loanResponses);
    }

    @Operation(summary = "Create a new loan", description = "Create a new loan with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created loan",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid loan details", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LoanResponse> create(@Validated @RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = this.loanService.create(loanRequest);
        return ResponseEntity.status(201).body(loanResponse);
    }

    @Operation(summary = "Get loan by ID", description = "Retrieve details of a specific loan by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved loan details",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<LoanResponse> get(@PathVariable Long id) {
        LoanResponse loanResponse = this.loanService.findById(id);
        return ResponseEntity.ok(loanResponse);
    }

    @Operation(summary = "Update an existing loan", description = "Update details of an existing loan by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated loan",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid update details", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PatchMapping(path = "/{id}")
    public ResponseEntity<LoanResponse> update(@Validated @RequestBody LoanRequest request, @PathVariable Long id) {
        LoanResponse loanResponse = this.loanService.update(request, id);
        return ResponseEntity.ok(loanResponse);
    }

    @Operation(summary = "Delete a loan by ID", description = "Delete a loan by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted loan", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loanService.delete(id);
        return ResponseEntity.ok().build();
    }
}
