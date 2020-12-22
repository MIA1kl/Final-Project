package sample.StudentTable.AddStudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database;
import sample.StudentTable.TblStudents;


public class AddStdController {
    @FXML private TextField bookId;
    @FXML private TextField stdID;
    @FXML private TextField stdName;
    @FXML private TextField stdSurname;
    @FXML private TextField dueDate;
    @FXML void addNewStudent(ActionEvent actionEvent) {
        Database.addStudent(new TblStudents(bookId.getText(),stdID.getText(), stdName.getText(), stdSurname.getText(), dueDate.getText()));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
