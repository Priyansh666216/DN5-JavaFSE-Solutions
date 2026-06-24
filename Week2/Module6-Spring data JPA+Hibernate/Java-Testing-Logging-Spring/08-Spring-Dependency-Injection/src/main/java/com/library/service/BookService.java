package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

public class BookService {

    // Step 2: BookRepository is declared but NOT set via constructor.
    // Spring will inject it by calling the setter below.
    private BookRepository bookRepository;

    // No-arg constructor — required for setter injection
    // Spring first calls this, then calls the setter
    public BookService() {
        System.out.println("[BookService] Bean instantiated by Spring (no-arg constructor).");
    }

    // -------------------------------------------------------
    // SETTER METHOD — Spring calls this automatically
    // because applicationContext.xml has:
    //   <property name="bookRepository" ref="bookRepository"/>
    //
    // The property name "bookRepository" maps to setBookRepository()
    // Spring handles this naming convention automatically.
    // -------------------------------------------------------
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] setBookRepository() called — dependency injected by Spring.");
    }

    // Getter so we can confirm the injection worked
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("[BookService] Title is empty, skipping.");
            return;
        }
        bookRepository.addBook(title);
    }

    public void listAllBooks() {
        List<String> books = bookRepository.getAllBooks();
        System.out.println("\n[BookService] Library catalogue (" + books.size() + " books):");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + books.get(i));
        }
        System.out.println();
    }

    public void removeBook(String title) {
        bookRepository.removeBook(title);
    }
}
