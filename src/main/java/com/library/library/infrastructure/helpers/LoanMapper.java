package com.library.library.infrastructure.helpers;

import com.library.library.api.dto.request.used_request.BookRequest;
import com.library.library.api.dto.request.used_request.LoanRequest;
import com.library.library.api.dto.response.used_responses.BookResponse;
import com.library.library.api.dto.response.used_responses.LoanResponse;
import com.library.library.domain.entities.Book;
import com.library.library.domain.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {
    Loan requestToEntity(LoanRequest loanRequest);
    LoanResponse entityToResponse(Loan loan);
}
