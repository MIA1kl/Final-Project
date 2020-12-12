package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {


        @FXML
        private TableView<tblinfo> table;

        @FXML
        private TableColumn<tblinfo, String> bookId;

        @FXML
        private TableColumn<tblinfo, String> bookName;

        @FXML
        private TableColumn<tblinfo, String> authorName;

        @FXML
        private TableColumn<tblinfo, Integer> totalAmount;

        @FXML
        private TableColumn<tblinfo, Integer> leftAmount;

        @FXML
        private Button btn1;


    @FXML
    void handleButtonAction(ActionEvent event) {
    try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Editor Window");
        stage.setScene(new Scene(root1));
        stage.show();
    }catch (Exception e){
        System.out.println("Can't open new window");
    }
    }
//    @FXML
//    void deleteRowFromTable(ActionEvent event) {
//
//    }



    @FXML
    void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking double click
        {
            Button btn2;
            System.out.println(table.getSelectionModel().getSelectedItem().getBookId());
            System.out.println(table.getSelectionModel().getSelectedItem().getBookName());
            System.out.println(table.getSelectionModel().getSelectedItem().getAuthorName());

        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Editor Window");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            System.out.println("Can't open new window");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookId.setCellValueFactory(new PropertyValueFactory<tblinfo, String>("bookId"));
        bookName.setCellValueFactory(new PropertyValueFactory<tblinfo, String>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<tblinfo, String>("authorName"));
        totalAmount.setCellValueFactory(new PropertyValueFactory<tblinfo, Integer>("totalAmount"));
        leftAmount.setCellValueFactory(new PropertyValueFactory<tblinfo, Integer>("leftAmount"));
        table.getItems().setAll(Database.init());
    }
}

