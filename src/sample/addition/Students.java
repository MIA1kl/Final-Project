package sample.addition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database;
import sample.Tblinfo;

import java.net.URL;
import java.util.ResourceBundle;

public class Students implements Initializable {
    @FXML private TextField bookName;
    @FXML private TextField authorName;
    @FXML private TextField totalAmount;
    @FXML private TextField leftAmount;
    @FXML private Button btnAdd;
    @FXML void addNewBook(ActionEvent actionEvent) {
        Database.addBook(new Tblinfo(Integer.toString(0), bookName.getText(), authorName.getText(), Integer.parseInt(totalAmount.getText()),Integer.parseInt(leftAmount.getText())));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
