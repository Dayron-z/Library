package com.library.library.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookToLoanResponse {
    private Long id;
    private String title;
    private Boolean status;
}
