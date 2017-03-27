package com.cskaoyan.week3.xmldatafromsever;

/**
 * Created by zhao on 2017/3/28.
 */

public class Book {

    String bookname;
    String author;
    int price;
    String personinfo;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", personinfo='" + personinfo + '\'' +
                '}';
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPersoninfo() {
        return personinfo;
    }

    public void setPersoninfo(String personinfo) {
        this.personinfo = personinfo;
    }
}
