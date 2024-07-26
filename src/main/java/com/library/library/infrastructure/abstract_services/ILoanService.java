package com.library.library.infrastructure.abstract_services;

import com.library.library.api.dto.request.used_request.LoanRequest;
import com.library.library.api.dto.response.used_responses.LoanResponse;

public interface ILoanService extends CrudService <LoanRequest, LoanResponse, Long> {
}
