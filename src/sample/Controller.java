package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable, Refreshable {


        @FXML private TableView<tblinfo> table;
        @FXML private TableColumn<tblinfo, String> bookId;
        @FXML private TableColumn<tblinfo, String> bookName;
        @FXML private TableColumn<tblinfo, String> authorName;
        @FXML private TableColumn<tblinfo, Integer> totalAmount;
        @FXML private TableColumn<tblinfo, Integer> leftAmount;
        @FXML private Button btn1;
        @FXML private Button btn2;
        @FXML private Button btn3;
        @FXML private Button btnRefresh;
        @FXML private TextField filterField;;
        private int currentId = -1;


    @FXML
    void handleButtonAction(ActionEvent event) {
    try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentWindow.fxml"));
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

    @FXML
    void handleButtonAdding(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/addition/SecondWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML

    public void updateTable(ActionEvent actionEvent) {
        table.getItems().setAll(Database.init());
        table.refresh();
    }

    @FXML
    public void deleteBook(ActionEvent actionEvent) {
        if (currentId != -1) {
            Database.deleteBook(currentId);
            table.getItems().setAll(Database.init());
        }

    }


    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/IAUlib","natalia","123123123");
    }



    @FXML
    void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1)
        {
            System.out.println(table.getSelectionModel().getSelectedItem().getBookId());
            currentId = Integer.parseInt(table.getSelectionModel().getSelectedItem().getBookId());
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


        FilteredList<tblinfo> filteredData = new FilteredList<>(table.getItems(), b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(tblinfo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(tblinfo.getBookName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (tblinfo.getAuthorName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(String.valueOf(tblinfo.getTotalAmount()).contains(lowerCaseFilter)){
                    return true;
                }
                else if(String.valueOf(tblinfo.getLeftAmount()).contains(lowerCaseFilter)){
                    return true;
                }
                else return false;

            });
        });

        SortedList<tblinfo> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
//        table.setItems(sortedData);
    }

    @Override
    public boolean isCurrent() {
        try {
            refresh();
        } catch (RefreshFailedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void refresh() throws RefreshFailedException {
        table.getItems().setAll(Database.init());
        table.refresh();
    }
}

