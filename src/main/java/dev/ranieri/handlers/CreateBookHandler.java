package dev.ranieri.handlers;

import com.google.gson.Gson;
import dev.ranieri.app.App;
import dev.ranieri.entities.Book;
import dev.ranieri.services.BookService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateBookHandler  implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Book book = gson.fromJson(json, Book.class);
        Book registeredBook = App.bookService.registerBook(book);
        String bookJson = gson.toJson(registeredBook);
        ctx.status(201);// status code for newly created content/objects
        ctx.result(bookJson);

    }
}
