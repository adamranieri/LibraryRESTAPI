package dev.ranieri.mockbasics;

import dev.ranieri.daos.BookDAO;
import dev.ranieri.daos.BookDaoPostgres;
import dev.ranieri.entities.Book;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockExampleTests {

    @Test
    void mock_1(){
        List<String> mockedList = Mockito.mock(List.class);
        Mockito.when(mockedList.get(99)).thenReturn("Bowser");
        System.out.println(mockedList.get(99));

    }

    @Test
    void register_books_must_have_a_title(){
        BookDAO bookDAO = Mockito.mock(BookDAO.class);
        Book book = new Book(0,"", "harper lee", 100);
        Mockito.when(bookDAO.createBook(book)).thenReturn(book);
        BookService bookService = new BookServiceImpl(bookDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            bookService.registerBook(book);
        });

    }

    @Test
    void sort_by_title(){
        BookDAO bookDAO = Mockito.mock(BookDAO.class);
        List<Book> fakeBooks = new ArrayList();
        fakeBooks.add(new Book(0,"A","x",0));
        fakeBooks.add(new Book(0,"A","y",0));
        fakeBooks.add(new Book(0,"B","z",0));
        Mockito.when(bookDAO.getAllBooks()).thenReturn(fakeBooks);

        BookService bookService = new BookServiceImpl(bookDAO);
        List<Book> sortedBooks =bookService.getBooksByTitle("A");
        Assertions.assertEquals(2,sortedBooks.size());

    }
}
