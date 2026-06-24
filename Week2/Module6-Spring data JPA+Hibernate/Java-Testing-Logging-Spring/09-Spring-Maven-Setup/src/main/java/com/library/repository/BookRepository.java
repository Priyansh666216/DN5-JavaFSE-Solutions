package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        books.add("Clean Code - Robert C. Martin");
        books.add("The Pragmatic Programmer - Andrew Hunt");
        books.add("Effective Java - Joshua Bloch");
        System.out.println("[BookRepository] Initialized with " + books.size() + " books.");
    }

    public void save(String title) {
        books.add(title);
        System.out.println("[BookRepository] Saved: " + title);
    }

    public List<String> findAll() {
        return new ArrayList<>(books);
    }

    public boolean delete(String title) {
        boolean removed = books.remove(title);
        System.out.println(removed
                ? "[BookRepository] Deleted: " + title
                : "[BookRepository] Not found: " + title);
        return removed;
    }
}
