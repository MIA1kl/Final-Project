package sample.StudentTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StdTableClass implements Initializable {
    @FXML private TableView<TblStudents> table1;
    @FXML private TableColumn<TblStudents, String> bookId;
    @FXML private TableColumn<TblStudents, String> stdId;
    @FXML private TableColumn<TblStudents, String> stdName;
    @FXML private TableColumn<TblStudents, String> stdSurname;
    @FXML private TableColumn<TblStudents, String> dueDate;
    @FXML private Button btnAdd;
    @FXML private Button btnDel;
    @FXML private Button btnRef;
    private String currentstdId = null;
    private int currentbookId = -1;

    @FXML
    void handleStdAdding(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/StudentTable/AddStudent/AddStd.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/CSS/styles.css");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void updateTable1(ActionEvent actionEvent) throws SQLException {
        table1.getItems().setAll(Database.init2());
        table1.refresh();
    }

    @FXML
    public void deleteStudent(ActionEvent actionEvent) throws SQLException {
        if (currentstdId != null) {
            Database.deleteStudent(currentstdId,currentbookId );
            table1.getItems().setAll(Database.init2());

        }

    }
    @FXML
    void clickItem1(MouseEvent event) {
        if (event.getClickCount() == 1)
        {
            System.out.println(table1.getSelectionModel().getSelectedItem().getStdId());
            currentstdId = table1.getSelectionModel().getSelectedItem().getStdId();
            currentbookId = Integer.parseInt(table1.getSelectionModel().getSelectedItem().getBookId());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookId.setCellValueFactory(new PropertyValueFactory<TblStudents, String>("bookId"));
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
