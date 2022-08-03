package dev.ranieri.services;

import dev.ranieri.entities.Book;

import java.util.List;

public interface BookService {

    Book registerBook(Book book);

    Book retrieveBookById(int id);

    boolean deleteBook(int id);

    Book modifyBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByTitle(String status);

}
