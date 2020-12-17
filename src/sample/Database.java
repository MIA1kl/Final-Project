package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    final static String DATABASE_URL = "jdbc:postgresql://localhost:5432/IAUlib";
    final static String user = "natalia";
    final static String pass = "123123123";
    static Connection connection = null;
    final static String SELECT_QUERY =
            "SELECT bookId, bookName, authorName, totalAmount, leftAmount FROM tblInfo";

    public static List<tblinfo> init() {
        Statement statement = null;
        List<tblinfo> tblInfo = new ArrayList<>();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DATABASE_URL, user, pass);
            }
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SELECT_QUERY);

            while (res.next()) {
                tblinfo a = new tblinfo();
                a.setBookId(Integer.toString(res.getInt("bookId")));
                a.setBookName(res.getString("bookName"));
                a.setAuthorName(res.getString("authorName"));
                a.setTotalAmount(res.getInt("totalAmount"));
                a.setLeftAmount(res.getInt("leftAmount"));
                tblInfo.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tblInfo;
    }
    public static void addBook(tblinfo book) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO tblInfo (bookName, authorName,totalAmount, leftAmount) " + "VALUES ('" + book.getBookName() +"','" + book.getAuthorName()+"','" + book.getTotalAmount() +"','" + book.getLeftAmount()  + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteBook(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM tblInfo where bookid=" + Integer.toString(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}