package com.library.controller;

import com.library.service.BookService;

import java.util.List;

// In a real Spring MVC app this would be annotated with @Controller
// and methods would have @RequestMapping annotations.
// Here we simulate the controller layer to show the 3-tier architecture.
public class BookController {

    private BookService bookService;

    public BookController() {
        System.out.println("[BookController] Bean created.");
    }

    // Setter injection — Spring calls this via <property name="bookService"/>
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
        System.out.println("[BookController] BookService injected via setter.");
    }

    // Simulates handling GET /books
    public void handleGetAllBooks() {
        System.out.println("\n[BookController] Handling GET /books request...");
        List<String> books = bookService.getAllBooks();
        System.out.println("[BookController] Returning " + books.size() + " book(s):");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + books.get(i));
        }
    }

    // Simulates handling POST /books
    public void handleAddBook(String title) {
        System.out.println("\n[BookController] Handling POST /books — title: " + title);
        bookService.addBook(title);
        System.out.println("[BookController] Book added successfully.");
    }

    // Simulates handling DELETE /books/{title}
    public void handleDeleteBook(String title) {
        System.out.println("\n[BookController] Handling DELETE /books/" + title);
        bookService.deleteBook(title);
    }
}
