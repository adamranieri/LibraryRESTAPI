package dev.ranieri.handlers;

import dev.ranieri.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteBookHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.bookService.deleteBook(id);
        if(result){
            ctx.status(204);// 204 is the status code meanding success but nothing is returned
        }else{
            ctx.status(400);
            ctx.result("Could not delete the book");
        }
    }
}
