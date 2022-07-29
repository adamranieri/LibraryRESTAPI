package dev.ranieri.handlers;

import com.google.gson.Gson;
import dev.ranieri.app.App;
import dev.ranieri.entities.Book;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateBookHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String bookJSON = ctx.body();
        Gson gson = new Gson();
        Book book = gson.fromJson(bookJSON, Book.class);
        Book updatedBook = App.bookService.modifyBook(book);
        String json = gson.toJson(updatedBook);
        ctx.result(json);
    }
}
