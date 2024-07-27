package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.used_request.LoanRequest;
import com.library.library.api.dto.response.used_responses.LoanResponse;
import com.library.library.infrastructure.abstract_services.ILoanService;
import com.library.library.utils.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class LoanService implements ILoanService {
    @Override
    public LoanResponse create(LoanRequest request) {
        return null;
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sortType) {
        return null;
    }

    @Override
    public LoanResponse findById(Long aLong) {
        return null;
    }


    public LoanResponse update(LoanRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
