package sample.StudentTable;

public class TblStudents {
    private String stdId;
    private String stdName;
    private String stdSurname;
    private String dueDate;
    private String bookId;



    public TblStudents( String bookId, String stdId, String stdName, String stdSurname, String dueDate) {
        this.bookId=bookId;
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdSurname = stdSurname;
        this.dueDate = dueDate;
    }

    public TblStudents() {
    }


    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdSurname() {
        return stdSurname;
    }

    public void setStdSurname(String stdSurname) {
        this.stdSurname = stdSurname;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
