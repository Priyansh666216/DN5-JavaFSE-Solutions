package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

// Service layer — contains business logic
// Depends on BookRepository which is injected by Spring via constructor
public class BookService {

    private final BookRepository bookRepository;

    // Constructor injection — Spring passes the BookRepository bean here
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Bean created. BookRepository injected successfully.");
    }

    public void addBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("[BookService] Invalid book title. Skipping.");
            return;
        }
        bookRepository.addBook(title);
    }

    public void listAllBooks() {
        List<String> books = bookRepository.getAllBooks();
        System.out.println("\n[BookService] All books in the library (" + books.size() + " total):");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + books.get(i));
        }
    }

    public void removeBook(String title) {
        boolean success = bookRepository.removeBook(title);
        if (!success) {
            System.out.println("[BookService] Could not remove — book does not exist.");
        }
    }
}
