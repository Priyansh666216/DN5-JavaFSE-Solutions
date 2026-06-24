package com.library.main;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("  Library Management — Setter Injection ");
        System.out.println("========================================\n");

        // Step 3: Load Spring context — beans are created and wired here
        // Watch the console: Spring calls the no-arg constructor first,
        // then calls setBookRepository() automatically
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n--- Spring context ready ---\n");

        // Retrieve BookService — BookRepository is already wired inside it
        BookService bookService = context.getBean("bookService", BookService.class);

        // Confirm injection worked — should NOT be null
        if (bookService.getBookRepository() != null) {
            System.out.println("[Main] Dependency Injection verified — BookRepository is NOT null.\n");
        }

        // Test the full flow
        bookService.listAllBooks();

        bookService.addBook("Spring in Action - Craig Walls");
        bookService.addBook("Head First Java - Kathy Sierra");

        bookService.listAllBooks();

        bookService.removeBook("Effective Java - Joshua Bloch");

        bookService.listAllBooks();

        System.out.println("========================================");
        System.out.println("  Application finished successfully.");
        System.out.println("========================================");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
