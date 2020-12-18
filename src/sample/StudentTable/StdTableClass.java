package sample.StudentTable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database;
import sample.Tblinfo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StdTableClass implements Initializable {
    @FXML private TableView<TblStudents> table1;
    @FXML private TableColumn<TblStudents, String> stdId;
    @FXML private TableColumn<TblStudents, String> stdName;
    @FXML private TableColumn<TblStudents, String> stdSurname;
    @FXML private TableColumn<TblStudents, String> dueDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stdId.setCellValueFactory(new PropertyValueFactory<TblStudents, String>("stdId"));
        stdName.setCellValueFactory(new PropertyValueFactory<TblStudents, String>("stdName"));
        stdSurname.setCellValueFactory(new PropertyValueFactory<TblStudents, String>("stdSurname"));
        dueDate.setCellValueFactory(new PropertyValueFactory<TblStudents, String >("dueDate"));
        try {
            table1.getItems().setAll(Database.init2());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
