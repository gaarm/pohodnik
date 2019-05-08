package excursion.controller;

import excursion.Bootstrap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField textFirstname;

    @FXML
    private TextField textSurname;

    @FXML
    private Label labelStatus;

    @FXML
    private void initialize() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        List<Person> personList = dbConnection.getPersons("");

        ObservableList<Person> data = FXCollections.observableList(personList);
        tableView.getItems().setAll(data);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            textFirstname.setText(tableView.getSelectionModel().getSelectedItem().getName());
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
        Person person = new Person(textFirstname.getText(), textSurname.getText());
        if (dbConnection.addPerson(person)) {
            labelStatus.setText("Uporabnik uspešno dodan!");
            tableView.getItems().add(person);
            textFirstname.setText("");
            textSurname.setText("");
        } else {
            labelStatus.setText("Napaka pri dodajanju!");
        }
    }

    @FXML
    private void handleEditAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setName(textFirstname.getText());
            tableView.getSelectionModel().getSelectedItem().setSurname(textSurname.getText());
            DBConnection dbConnection = new DBConnection();
            if (dbConnection.updatePerson(tableView.getSelectionModel().getSelectedItem())) {
                tableView.getItems().set(selectedIndex, tableView.getSelectionModel().getSelectedItem());
                labelStatus.setText("Uspešen update");
            }
        }
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();
            if (dbConnection.deletePerson(tableView.getSelectionModel().getSelectedItem())) {
                labelStatus.setText("Uspešen delete");
                tableView.getItems().remove(selectedIndex);
            }
        }
    }

    @FXML
    public void clickMenuItemHike(ActionEvent event) throws IOException {
        Stage stage = (Stage) textFirstname.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Excursion.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle(Bootstrap.APP_TITLE);
        stage.setResizable(false);
        stage.show();
    }
}
