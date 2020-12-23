package sample;

import sample.StudentTable.TblStudents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    final static String DATABASE_URL = "jdbc:postgresql://localhost:5432/IAUlib";
    final static String user = "natalia";
    final static String pass = "123123123";
    static Connection connection = null;
    final static String SELECT_QUERY =
            "SELECT bookId, bookName, authorName, totalAmount, leftAmount FROM tblInfo ORDER BY bookId;";
    final static String SELECT_QUERY2 =
            "SELECT\n" +
                    "\ttblstdlib.bookId,\n" +
                    "\ttblstudents.stdId,\n" +
                    "\tstdName,\n" +
                    "\tstdSurname,\n" +
                    "\tduedate\n" +
                    "FROM\n" +
                    "\ttblstdlib\n" +
                    "LEFT OUTER JOIN tblStudents\n" +
                    "    ON tblStudents.stdId = tblstdlib.stdId\n"+
                    "ORDER BY bookId;";

    public static List<TblStudents> init2() throws SQLException {
        Statement statement = null;
        List<TblStudents>  tblStudents = new ArrayList<>();
        statement = connection.createStatement();

        assert false;
        ResultSet res1 = statement.executeQuery(SELECT_QUERY2);
            while (res1.next()) {
                TblStudents q = new TblStudents();
                q.setBookId(res1.getString("bookId"));
                q.setStdId(res1.getString("stdId"));
                q.setStdName(res1.getString("stdName"));
                q.setStdSurname(res1.getString("stdSurname"));
                q.setDueDate(res1.getString("dueDate"));
                tblStudents.add(q);
            }

        return tblStudents;
    }

    public static List<Tblinfo> init1() throws SQLException {
        Statement statement = null;
        List<Tblinfo> tblInfo = new ArrayList<>();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DATABASE_URL, user, pass);
            }
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SELECT_QUERY);

            while (res.next()) {
                Tblinfo a = new Tblinfo();
                a.setBookId(Integer.toString(res.getInt("bookId")));
                a.setBookName(res.getString("bookName"));
                a.setAuthorName(res.getString("authorName"));
                a.setTotalAmount(res.getInt("totalAmount"));
                a.setLeftAmount(res.getInt("leftAmount"));
                tblInfo.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tblInfo;
    }

    public static void addBook(Tblinfo book) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO tblInfo (bookName, authorName,totalAmount, leftAmount) " + "VALUES ('" + book.getBookName() +"','" + book.getAuthorName()+"','" + book.getTotalAmount() +"','" + book.getLeftAmount()  + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addStudent(TblStudents student) {
        try {
            Statement statement = connection.createStatement();
            try{statement.executeUpdate("INSERT INTO tblStudents(stdId, stdName, stdSurname, dueDate) " + "VALUES ('" + student.getStdId() +"','" + student.getStdName()+"','" + student.getStdSurname() +"','" + student.getDueDate()  + "')");
            }catch (Exception e){
                System.out.println("This student is already in this table");
            }
            statement.executeUpdate("INSERT INTO tblStdLib(bookId, stdId)  " + "VALUES ('" + student.getBookId() +"','" + student.getStdId()+ "')");
            statement.executeUpdate("UPDATE tblInfo SET leftAmount = LeftAmount-1 WHERE bookid ="+student.getBookId()+";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteBook(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM tblstdlib where bookId=" + Integer.toString(id));
            statement.executeUpdate("DELETE FROM tblInfo where bookId=" + Integer.toString(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteStudent(String id , int b_id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM tblstdlib where stdId=" + "'"+id+"'" +"AND bookId =" +Integer.toString(b_id));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
