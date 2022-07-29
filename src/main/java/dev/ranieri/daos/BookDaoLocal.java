package dev.ranieri.daos;

import dev.ranieri.entities.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoLocal implements BookDAO{

    private Map<Integer,Book> bookTable = new HashMap();
    private int idMaker = 1;

    @Override
    public Book createBook(Book book) {
        book.setId(idMaker);// set the book id to not zero.
        idMaker++;// increment the next id by doing ++
        bookTable.put(book.getId(), book);
        System.out.println(bookTable.values());
        return book;
    }

    @Override
    public Book getBookById(int id) {
        return bookTable.get(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        bookTable.put(book.getId(), book);// overrwrites that spot in the map
        return book;
    }

    @Override
    public boolean deleteBookById(int id) {
        Book book = bookTable.remove(id);// the remove method returns the object that was removed from the map
        if(book == null){
            return false;
        }
        return true;
    }
}
