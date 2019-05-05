package excursion.controller;

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
import excursion.DBConnection;
import excursion.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonController {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private TextField actionTarget;

    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            textName.setText(tableView.getSelectionModel().getSelectedItem().getName());
            textSurname.setText(tableView.getSelectionModel().getSelectedItem().getSurname());
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
        Person person = new Person(textName.getText(), textSurname.getText());
        if (dbConnection.addPerson(person)) {
            actionTarget.setText("Uporabnik uspešno dodan!");
            tableView.getItems().add(person);
            textName.setText("");
            textSurname.setText("");

        } else {
            actionTarget.setText("Napaka pri dodajanju!");
        }
    }

    @FXML
    private void handleEditAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setName(textName.getText());
            tableView.getSelectionModel().getSelectedItem().setSurname(textSurname.getText());
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
    public void clickMenuItemHike(ActionEvent event) throws IOException {
        Stage stage = (Stage) textName.getScene().getWindow();
        stage.close();

        Stage stageExcursion = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Excursion.fxml"));
        stage.setScene(new Scene(root));

        //Fill stage with content
        stage.show();
    }
}
