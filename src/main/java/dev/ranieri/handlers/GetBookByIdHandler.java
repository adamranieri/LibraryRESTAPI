package dev.ranieri.handlers;

import com.google.gson.Gson;
import dev.ranieri.app.App;
import dev.ranieri.entities.Book;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetBookByIdHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Book book = App.bookService.retrieveBookById(id);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        ctx.result(json);
    }
}
