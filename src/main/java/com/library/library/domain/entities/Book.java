package com.library.library.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate publication_date;
    @Column(nullable = false)
    private Boolean status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "author_id" , referencedColumnName = "id")
    private Author author;
}
