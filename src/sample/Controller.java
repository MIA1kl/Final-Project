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
import sample.StudentTable.StdTableClass;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable{

        @FXML private TableView<Tblinfo> table;
        @FXML private TableColumn<Tblinfo, String> bookId;
        @FXML private TableColumn<Tblinfo, String> bookName;
        @FXML private TableColumn<Tblinfo, String> authorName;
        @FXML private TableColumn<Tblinfo, Integer> totalAmount;
        @FXML private TableColumn<Tblinfo, Integer> leftAmount;
        @FXML private Button btn1;
        @FXML private Button btn2;
        @FXML private Button btn3;
        @FXML private Button btnRefresh;
        @FXML private TextField filterField;;
        private int currentId = -1;


    @FXML
    void handleButtonAction(ActionEvent event) {
        Parent root= null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/StudentTable/StdTableWindow.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/CSS/styles.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonAdding(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/addition/SecondWindow.fxml"));
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

    public void updateTable(ActionEvent actionEvent) throws SQLException {
        table.getItems().setAll(Database.init1());
        table.refresh();
    }

    @FXML
    public void deleteBook(ActionEvent actionEvent) throws SQLException {
        if (currentId != -1) {
            Database.deleteBook(currentId);
            table.getItems().setAll(Database.init1());
        }

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
        bookId.setCellValueFactory(new PropertyValueFactory<Tblinfo, String>("bookId"));
        bookName.setCellValueFactory(new PropertyValueFactory<Tblinfo, String>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<Tblinfo, String>("authorName"));
        totalAmount.setCellValueFactory(new PropertyValueFactory<Tblinfo, Integer>("totalAmount"));
        leftAmount.setCellValueFactory(new PropertyValueFactory<Tblinfo, Integer>("leftAmount"));
        try {
            table.getItems().setAll(Database.init1());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        FilteredList<Tblinfo> filteredData = new FilteredList<>(table.getItems(), b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(Tblinfo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(Tblinfo.getBookName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (Tblinfo.getAuthorName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(String.valueOf(Tblinfo.getTotalAmount()).contains(lowerCaseFilter)){
                    return true;
                }
                else if(String.valueOf(Tblinfo.getLeftAmount()).contains(lowerCaseFilter)){
                    return true;
                }
                else return false;

            });
        });

        SortedList<Tblinfo> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
//        table.setItems(sortedData);
    }

}

