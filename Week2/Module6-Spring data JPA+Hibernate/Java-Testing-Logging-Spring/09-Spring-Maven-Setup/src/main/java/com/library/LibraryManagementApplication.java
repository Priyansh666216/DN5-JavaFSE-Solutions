package com.library;

import com.library.controller.BookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  LibraryManagement — Maven + Spring Setup  ");
        System.out.println("============================================\n");

        // Load Spring context — all beans from applicationContext.xml
        // are created and wired here in the correct order
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n--- All beans created and dependencies wired ---\n");

        // Get the controller — it already has BookService,
        // which already has BookRepository injected inside it
        BookController controller = context.getBean("bookController", BookController.class);

        // Simulate HTTP requests flowing through Controller → Service → Repository
        controller.handleGetAllBooks();

        controller.handleAddBook("Spring in Action - Craig Walls");
        controller.handleAddBook("Head First Design Patterns - Freeman");

        controller.handleGetAllBooks();

        controller.handleDeleteBook("Clean Code - Robert C. Martin");

        controller.handleGetAllBooks();

        System.out.println("\n============================================");
        System.out.println("  Application finished.");
        System.out.println("============================================");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
