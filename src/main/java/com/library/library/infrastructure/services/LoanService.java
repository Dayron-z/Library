package com.library.library.infrastructure.services;

import com.library.library.api.dto.request.used_request.LoanRequest;
import com.library.library.api.dto.response.used_responses.LoanResponse;
import com.library.library.domain.entities.Author;
import com.library.library.domain.entities.Book;
import com.library.library.domain.entities.Loan;
import com.library.library.domain.entities.UserEntity;
import com.library.library.domain.repositories.AuthorRepository;
import com.library.library.domain.repositories.BookRepository;
import com.library.library.domain.repositories.LoanRepository;
import com.library.library.domain.repositories.UserRepository;
import com.library.library.infrastructure.abstract_services.ILoanService;
import com.library.library.infrastructure.helpers.BookMapper;
import com.library.library.infrastructure.helpers.LoanMapper;
import com.library.library.utils.enums.SortType;
import com.library.library.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final LoanMapper loanMapper;

    @Override
    public LoanResponse create(LoanRequest request) {
        UserEntity user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("The user with the specified id was not found"));
        Book book = this.bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BadRequestException("The book with the specified id was not found"));
        book.setStatus(true);

        Loan loan = this.loanMapper.requestToEntity(request);
        loan.setUser(user);
        loan.setBook(book);
        return this.loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pageable = null;


        switch (sortType) {
            case ASC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
            case NONE -> pageable = PageRequest.of(page, size);
        }

        return this.loanRepository.findAll(pageable).map(loan -> this.loanMapper.entityToResponse(loan));
    }

    @Override
    public LoanResponse findById(Long id) {
        Loan loan = this.getById(id);
        return this.loanMapper.entityToResponse(loan);
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        UserEntity user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("The user with the specified id was not found"));
        Book book = this.bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BadRequestException("The book with the specified id was not found"));
        Loan loan = this.getById(id);
        loan.setUser(user);
        loan.setBook(book);
        loan.setEndDate(request.getEndDate());


        return this.loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public void delete(Long id) {
        Loan loan = this.getById(id);
        this.loanRepository.delete(loan);
    }


    private Loan getById(Long id){
        return this.loanRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The loan with the specified id was not found"));
    }
}
