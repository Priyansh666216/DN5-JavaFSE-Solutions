package com.library.repository;

import java.util.ArrayList;
import java.util.List;

// Repository layer — responsible for data access (simulated with an in-memory list)
// In a real app this would talk to a database using JPA/Hibernate
public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        // Pre-load some sample books
        books.add("Clean Code - Robert C. Martin");
        books.add("The Pragmatic Programmer - Andrew Hunt");
        books.add("Effective Java - Joshua Bloch");
        System.out.println("[BookRepository] Bean created and sample data loaded.");
    }

    public void addBook(String title) {
        books.add(title);
        System.out.println("[BookRepository] Book added: " + title);
    }

    public List<String> getAllBooks() {
        return books;
    }

    public boolean removeBook(String title) {
        boolean removed = books.remove(title);
        if (removed) {
            System.out.println("[BookRepository] Book removed: " + title);
        } else {
            System.out.println("[BookRepository] Book not found: " + title);
        }
        return removed;
    }
}
