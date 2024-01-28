package com.ahmed.bookstore.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BookRequest {


    private String title;

    private String author;

    private String isbn;
}
