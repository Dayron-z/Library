package com.library.library.api.dto.response.used_responses;

import com.library.library.api.dto.response.custom_responses.BookToLoanResponse;
import com.library.library.api.dto.response.custom_responses.UserToLoanResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookToLoanResponse book;
    private UserToLoanResponse user;

}
