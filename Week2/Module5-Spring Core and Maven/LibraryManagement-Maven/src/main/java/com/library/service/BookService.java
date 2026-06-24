package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        System.out.println("[BookService] Bean created.");
    }

    // Setter injection — Spring calls this via <property name="bookRepository"/>
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] BookRepository injected via setter.");
    }

    public void addBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("[BookService] Invalid title, skipping.");
            return;
        }
        bookRepository.save(title);
    }

    public List<String> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(String title) {
        bookRepository.delete(title);
    }
}
