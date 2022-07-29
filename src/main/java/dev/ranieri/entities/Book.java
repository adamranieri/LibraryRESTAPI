package dev.ranieri.entities;

public class Book {

    private int id;
    private String title;
    private String author;
    private long returnDate; // time is stored as number in unix epoch

    public Book() {
    }

    public Book(int id, String title, String author, long returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(long returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", returnDate=" + returnDate +
                '}';
    }
}
