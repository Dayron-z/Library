package com.library.library.api.dto.response.used_responses;

import com.library.library.api.dto.response.custom_responses.AuthorToBookResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private LocalDate publication_date;
    private Boolean status;
    private AuthorToBookResponse author;
}
