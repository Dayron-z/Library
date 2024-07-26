package com.library.library.domain.repositories;

import com.library.library.domain.entities.Book;
import com.library.library.domain.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
