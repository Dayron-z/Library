package com.library.library.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookToAuthorResponse {
    private Long id;
    private String title;
    private LocalDate publication_date;
    private Boolean status;
}
