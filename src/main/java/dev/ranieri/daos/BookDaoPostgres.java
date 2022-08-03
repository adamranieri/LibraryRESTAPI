package dev.ranieri.daos;

import dev.ranieri.entities.Book;
import dev.ranieri.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoPostgres implements BookDAO{

    @Override
    public Book createBook(Book book) {
        //try with resources syntax. It automatically closes the connection at the end of the try
        // or at the end of the catch
        try(Connection conn = ConnectionUtil.createConnection()){
            //insert into book values (default, 'The Stranger', 'Albert Camus', 0);
            String sql = "insert into book values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3,book.getReturnDate());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();// returns the id that was created
            rs.next();// you have to move the curosr to the first valid record

            int generatedKey = rs.getInt("id");
            book.setId(generatedKey);
            return book;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from book where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setReturnDate(rs.getLong("return_date"));

            return book;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from book";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Book> bookList = new ArrayList();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setReturnDate(rs.getLong("return_date"));
                bookList.add(book);
            }
            return bookList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try(Connection conn = ConnectionUtil.createConnection()){
            //update book set title = 'The Stranger Things', author = 'Albert Kamus', return_date = 1 where id = 1;
            String sql = "update book set title = ?, author = ?, return_date = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3,book.getReturnDate());
            preparedStatement.setInt(4,book.getId());

            preparedStatement.executeUpdate();
            return book;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteBookById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from book where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
}
