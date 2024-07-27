package com.library.library.api.dto.request.used_request;


import com.library.library.domain.entities.Book;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    @NotBlank(message = "Name cannot must be blank")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotNull(message = "Birth date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}
