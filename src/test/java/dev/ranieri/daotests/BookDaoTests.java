package dev.ranieri.daotests;

import dev.ranieri.daos.BookDAO;
import dev.ranieri.daos.BookDaoPostgres;
import dev.ranieri.entities.Book;
import dev.ranieri.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// @ means Annotation
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTests {

    static BookDAO bookDAO = new BookDaoPostgres();

    @BeforeAll // this method will execute before any tests ordered or unordered
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table book(\n" +
                    "\tid serial primary key,\n" +
                    "\ttitle varchar(100) not null,\n" +
                    "\tauthor varchar(100) not null,\n" +
                    "\treturn_date int default 0\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    @Order(1)
    void create_book_test(){
        // By convention if the ID of any entity is 0 it is implied that it has not been saved
        // return date is 0 to indicate that it is currently in the library
        Book book = new Book(0,"Frankenstein", "Mary Shelley", 0);
        Book savedBook = bookDAO.createBook(book);
        Assertions.assertNotEquals(0,savedBook.getId()) ;
        System.out.println(book);
    }

    @Test
    @Order(2)
    void get_book_by_id_test(){
        Book book = bookDAO.getBookById(1);// we will assume we saved the first book
        Assertions.assertEquals("Frankenstein", book.getTitle());
    }

    @Test
    @Order(3)
    void update_book_test(){
        // think of update as more of a replacement or swap
        Book bookv2 = new Book(1,"Frankestein 2: The Waifu of Frankenstein", "Mary Shelley", 0);
        bookDAO.updateBook(bookv2);
        Book book = bookDAO.getBookById(1);
        Assertions.assertEquals("Frankestein 2: The Waifu of Frankenstein", book.getTitle());

    }

    @Test
    @Order(4)
    void delete_book_by_id_test(){
        boolean result = bookDAO.deleteBookById(1);
        Assertions.assertTrue(result);

    }

    @AfterAll // runs after the last test finishes
    static void teardown(){

        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table book";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
