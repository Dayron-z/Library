package com.library.library.api.dto.request.used_request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotNull(message = "Publication date cannot be null")
    @PastOrPresent(message = "Publication date must be in the past or present")
    private LocalDate publication_date;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    @NotNull(message = "Author ID cannot be null")
    @Positive(message = "Author ID must be a positive number")
    private Long authorId;
}
