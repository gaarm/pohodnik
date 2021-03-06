package hiking.controller;

import hiking.Main;
import hiking.Localization;
import hiking.model.Excursion;
import hiking.model.PersonExcursion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import hiking.DBConnection;
import hiking.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonController {

    private static final String cbId = "cbId-";

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
    private HBox hBox;

    @FXML
    private void initialize() throws SQLException {

        DBConnection dbConnection = new DBConnection();
        List<Person> personList = dbConnection.getPersons("");
        List<Excursion> excursionList = dbConnection.getExcursions("");

        ObservableList<Person> data = FXCollections.observableList(personList);
        tableView.getItems().setAll(data);

        for (Excursion excursion : excursionList) {
            CheckBox cb = new CheckBox(excursion.getName());
            cb.setId(PersonController.cbId + excursion.getId());
            hBox.getChildren().add(cb);
        }

        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
            textFirstname.setText(selectedPerson.getName());
            textSurname.setText(selectedPerson.getSurname());
            try {
                List<PersonExcursion> personExcursionList = dbConnection.getPersonExcursions(selectedPerson);
                for (Node node : hBox.getChildren()) {
                    ((CheckBox) node).setSelected(false);
                    for (PersonExcursion personExcursion : personExcursionList) {
                        if (node.getId().equals(PersonController.cbId + personExcursion.getExcursionId())) {
                            ((CheckBox) node).setSelected(true);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        List<Person> personList = dbConnection.getPersons(textSearch.getText().trim());

        ObservableList<Person> data = FXCollections.observableList(personList);
        tableView.getItems().setAll(data);
        labelStatus.setText("");
    }

    @FXML
    protected void handleAddAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Person person = new Person(textFirstname.getText(), textSurname.getText());
        if (dbConnection.addPerson(person)) {
            labelStatus.setText(Localization.APP_MSG_OPERATION_ADD_SUCCESS);
            tableView.getItems().add(person);
            textFirstname.setText("");
            textSurname.setText("");
        } else {
            labelStatus.setText(Localization.APP_MSG_OPERATION_ADD_ERROR);
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
                labelStatus.setText(Localization.APP_MSG_OPERATION_UPD_SUCCESS);
            }
        }
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();
            if (dbConnection.deletePerson(tableView.getSelectionModel().getSelectedItem())) {
                labelStatus.setText(Localization.APP_MSG_OPERATION_DEL_SUCCESS);
                tableView.getItems().remove(selectedIndex);
            }
        }
    }

    @FXML
    public void clickMenuItemHike(ActionEvent event) throws IOException {
        Stage stage = (Stage) textFirstname.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Excursion.fxml"));
        stage.setScene(new Scene(root, Main.APP_SCENE_WIDTH, Main.APP_SCENE_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void handleSaveExcursionsAction(ActionEvent actionEvent) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();

            for (Node node : hBox.getChildren()) {
                String[] cbId = ((CheckBox) node).getId().split("-");
                int excursionId = Integer.parseInt(cbId[1]);
                dbConnection.deletePersonExcursion(selectedPerson, excursionId);
                if (((CheckBox) node).isSelected()) {
                    dbConnection.addPersonExcursion(selectedPerson, excursionId);
                }
            }
        }
    }
}
