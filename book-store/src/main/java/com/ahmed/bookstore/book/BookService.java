package com.ahmed.bookstore.book;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(BookRequest bookRequest) {
        var book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .isbn(bookRequest.getIsbn())
                .build();

        return bookRepository.save(book);
    }

    public List<Book> findAll() {

        return bookRepository.findAll();
    }

}
