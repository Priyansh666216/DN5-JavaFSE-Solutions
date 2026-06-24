package com.library.main;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApp {

    public static void main(String[] args) {

        System.out.println("======= Starting Spring Application Context =======\n");

        // Load the Spring context from applicationContext.xml
        // Spring reads the XML, creates all beans, and wires dependencies
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n======= Context Loaded Successfully =======\n");

        // Retrieve the BookService bean from the context
        // Spring has already injected BookRepository into it
        BookService bookService = (BookService) context.getBean("bookService");

        // --- Use the service ---

        // List pre-loaded books
        bookService.listAllBooks();

        // Add new books
        System.out.println();
        bookService.addBook("Spring in Action - Craig Walls");
        bookService.addBook("Head First Design Patterns - Freeman & Freeman");

        // List again after adding
        bookService.listAllBooks();

        // Remove a book
        System.out.println();
        bookService.removeBook("Clean Code - Robert C. Martin");

        // Final list
        bookService.listAllBooks();

        System.out.println("\n======= Application Finished =======");

        // Close the context to release resources
        ((ClassPathXmlApplicationContext) context).close();
    }
}
