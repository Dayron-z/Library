package com.library.library.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToLoanResponse {
    private Long id;
    private String name;
    private String email;
}
