package sample;

import java.lang.reflect.AnnotatedArrayType;

public class Tblinfo {
    private String bookId;
    private String bookName;
    private String authorName;
    private int totalAmount;
    private int leftAmount;

    public Tblinfo(String bookId, String bookName, String authorName, int totalAmount, int leftAmount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.totalAmount = totalAmount;
        this.leftAmount = leftAmount;
    }

    public Tblinfo() {

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
    }
}