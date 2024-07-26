package com.library.library.domain.repositories;

import com.library.library.domain.entities.Author;
import com.library.library.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
