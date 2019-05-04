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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import myapp.DBConnection;
import myapp.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonController {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textIme;

    @FXML
    private TextField textPriimek;

    @FXML
    private TextField actionTarget;

    @FXML
    private BorderPane borderPaneOseba;
    @FXML
    private BorderPane borderPanePohod;

    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            textIme.setText(tableView.getSelectionModel().getSelectedItem().getName());
            textPriimek.setText(tableView.getSelectionModel().getSelectedItem().getSurname());

            System.out.println(tableView.getSelectionModel().getSelectedItem().getId());

        });
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Person> personList = dbConnection.getPersons(textSearch.getText().trim());

        ObservableList<Person> data = FXCollections.observableList(personList);
        tableView.getItems().setAll(data);
    }

    @FXML
    protected void handleAddAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Person person = new Person(textIme.getText(), textPriimek.getText());
        if (dbConnection.addPerson(person)) {
            actionTarget.setText("Uporabnik uspešno dodan!");
            tableView.getItems().add(person);
            textIme.setText("");
            textPriimek.setText("");

        } else {
            actionTarget.setText("Napaka pri dodajanju!");
        }
    }

    @FXML
    private void handleEditAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setName(textIme.getText());
            tableView.getSelectionModel().getSelectedItem().setSurname(textPriimek.getText());
            DBConnection dbConnection = new DBConnection();
            dbConnection.updatePerson(tableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();
            dbConnection.deletePerson(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    public void clickMenuItemOseba(ActionEvent event) {
        System.out.println("x");
        borderPaneOseba.setVisible(false);
    }

    @FXML
    public void clickMenuItemPohod(ActionEvent event) throws IOException {
        System.out.println("x");
        Stage stage = (Stage) textIme.getScene().getWindow();
        stage.close();

        Stage stagePohod = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Hike.fxml"));
        stage.setScene(new Scene(root));

        //Fill stage with content
        stage.show();
    }
}
