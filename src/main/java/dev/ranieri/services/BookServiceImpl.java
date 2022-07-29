package dev.ranieri.services;

import dev.ranieri.daos.BookDAO;
import dev.ranieri.entities.Book;

public class BookServiceImpl implements BookService{
    // in order to to implement these methods I need to use a book dao that has crud operations
    // Dependency Injection. Building an instance of the service with the dependency in the constructor
    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Override
    public Book registerBook(Book book) {

        // business rules. Human logic
        if(book.getTitle().length() == 0){
            throw new RuntimeException("Book title cannot be empty");
        }

        if(book.getAuthor().length() == 0 ){
            throw new RuntimeException("Book length cannot be empty");
        }

        Book savedBook = this.bookDAO.createBook(book);

        return savedBook;
    }

    @Override
    public Book retrieveBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public boolean deleteBook(int id) {
        boolean isSuccessful = this.bookDAO.deleteBookById(id);
        return isSuccessful;
    }

    @Override
    public Book modifyBook(Book book) {

        if(book.getTitle().length() == 0){
            throw new RuntimeException("Book title cannot be empty");
        }

        if(book.getAuthor().length() == 0 ){
            throw new RuntimeException("Book length cannot be empty");
        }

        return this.bookDAO.updateBook(book);
    }
}
