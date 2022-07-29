package dev.ranieri.app;

import dev.ranieri.daos.BookDaoLocal;
import dev.ranieri.handlers.CreateBookHandler;
import dev.ranieri.handlers.DeleteBookHandler;
import dev.ranieri.handlers.GetBookByIdHandler;
import dev.ranieri.handlers.UpdateBookHandler;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import io.javalin.Javalin;

public class App {

    // you can use this single instance throughout your application
    public static BookService bookService = new BookServiceImpl(new BookDaoLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();


        CreateBookHandler createBookHandler = new CreateBookHandler();
        GetBookByIdHandler getBookByIdHandler = new GetBookByIdHandler();
        DeleteBookHandler deleteBookHandler = new DeleteBookHandler();
        UpdateBookHandler updateBookHandler = new UpdateBookHandler();

        app.post("/books", createBookHandler);
        app.get("/books/{id}", getBookByIdHandler);
        // vertical slices. Complete one route at a time
        app.delete("/books/{id}", deleteBookHandler);
        app.put("/books/{id}", updateBookHandler);

        app.start();
    }
}
