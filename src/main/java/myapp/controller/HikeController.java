package myapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myapp.DBConnection;
import myapp.model.Hike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HikeController {

    @FXML
    private TableView<Hike> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textNaziv;

    @FXML
    private TextField actionTarget;


    @FXML
    private void handleSearchAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Hike> hikeList = dbConnection.getHikes(textSearch.getText().trim());

        ObservableList<Hike> data = FXCollections.observableList(hikeList);
        tableView.getItems().setAll(data);
    }

    @FXML
    protected void handleAddAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Hike hike = new Hike(textNaziv.getText());
        if (dbConnection.addHike(hike)) {
            actionTarget.setText("Pohod uspešno dodan!");
            tableView.getItems().add(hike);
            textNaziv.setText("");
        } else {
            actionTarget.setText("Napaka pri dodajanju!");
        }
    }
    @FXML
    private void handleEditAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setName(textNaziv.getText());
            DBConnection dbConnection = new DBConnection();
            dbConnection.updateHike(tableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();
            dbConnection.deleteHike(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    public void clickMenuItemPerson(ActionEvent event) throws IOException {
        System.out.println("x");
        Stage stage = (Stage) textNaziv.getScene().getWindow();
        stage.close();

        Stage stagePohod = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Hike.fxml"));
        stage.setScene(new Scene(root));

        //Fill stage with content
        stage.show();
    }
}
