package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        books.add("Clean Code - Robert C. Martin");
        books.add("The Pragmatic Programmer - Andrew Hunt");
        books.add("Effective Java - Joshua Bloch");
        System.out.println("[BookRepository] Bean instantiated by Spring.");
    }

    public void addBook(String title) {
        books.add(title);
        System.out.println("[BookRepository] Added: " + title);
    }

    public List<String> getAllBooks() {
        return books;
    }

    public boolean removeBook(String title) {
        boolean removed = books.remove(title);
        System.out.println(removed
                ? "[BookRepository] Removed: " + title
                : "[BookRepository] Not found: " + title);
        return removed;
    }
}
