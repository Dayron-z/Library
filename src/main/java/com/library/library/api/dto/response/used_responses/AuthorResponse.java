package com.library.library.api.dto.response.used_responses;

import com.library.library.api.dto.response.custom_responses.BookToAuthorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<BookToAuthorResponse> books;
}
