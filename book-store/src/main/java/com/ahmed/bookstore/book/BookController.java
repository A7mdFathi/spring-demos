package com.ahmed.bookstore.book;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> save(@RequestBody BookRequest bookRequest) {

       

        Book book = bookService.save(bookRequest);

        return ResponseEntity.ok(book);

    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> findAll() {

        return ResponseEntity.ok(bookService.findAll());

    }
}
