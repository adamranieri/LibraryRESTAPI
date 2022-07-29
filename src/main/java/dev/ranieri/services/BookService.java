package dev.ranieri.services;

import dev.ranieri.entities.Book;

public interface BookService {

    Book registerBook(Book book);

    Book retrieveBookById(int id);

    boolean deleteBook(int id);

    Book modifyBook(Book book);
}
