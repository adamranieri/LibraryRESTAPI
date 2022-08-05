package dev.ranieri.app;

import com.google.gson.Gson;
import dev.ranieri.daos.BookDaoLocal;
import dev.ranieri.daos.BookDaoPostgres;
import dev.ranieri.entities.Book;
import dev.ranieri.handlers.*;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class App {

    // you can use this single instance throughout your application
    public static BookService bookService = new BookServiceImpl(new BookDaoPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
        });


        CreateBookHandler createBookHandler = new CreateBookHandler();
        GetBookByIdHandler getBookByIdHandler = new GetBookByIdHandler();
        DeleteBookHandler deleteBookHandler = new DeleteBookHandler();
        UpdateBookHandler updateBookHandler = new UpdateBookHandler();

        // first parameter route, second parameter is the functionality we want to call when that route is activated
        app.post("/books", createBookHandler);
        app.get("/books/{id}", getBookByIdHandler);
        // vertical slices. Complete one route at a time
        app.delete("/books/{id}", deleteBookHandler);
        app.put("/books/{id}", updateBookHandler);

        //passing functionality into another method is very common in other programming
        // lambdas allow us to define a block of code to be stored as a VALUE in a variable
        // We can directly create an implementation of the Handler interface with a lambda

        // Handler is a functional interface. it is an interface with on and only one abstract method to implement
        // if the interface only has a single input parameter the paranetheses are optional

        Handler helloHandler = ctx -> {
            ctx.result("Hello everyone");
        };

        app.get("/hello",helloHandler);

        Handler getAllBooks = ctx ->{
            String title = ctx.queryParam("title");
            Gson gson = new Gson();

            if(title == null){
                List<Book> books = App.bookService.getAllBooks();
                String json = gson.toJson(books);
                ctx.result(json);
            }else{
                List<Book> books = App.bookService.getBooksByTitle(title);
                String json = gson.toJson(books);
                ctx.result(json);
            }

        };

        // queryParams DO NOT require a new route to be declared
        app.get("/books", getAllBooks);

        app.start();
    }
}
