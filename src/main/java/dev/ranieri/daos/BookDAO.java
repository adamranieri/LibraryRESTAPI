package dev.ranieri.daos;

import dev.ranieri.entities.Book;

import java.util.List;

// Data Access Object
// It will support the basic crud operations for our entity
// One DAO per Entity in your project
public interface BookDAO {

    //Create
    Book createBook(Book book);

    //Read
    Book getBookById(int id);
    List<Book> getAllBooks();

    //Update
    Book updateBook(Book book);

    //Delete
    boolean deleteBookById(int id);

}
