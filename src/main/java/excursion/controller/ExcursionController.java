package excursion.controller;

import excursion.Main;
import excursion.Localization;
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
import excursion.model.Excursion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ExcursionController {

    @FXML
    private TableView<Excursion> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textName;

    @FXML
    private Label labelStatus;


    @FXML
    private void initialize() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        List<Excursion> excursionList = dbConnection.getExcursions(textSearch.getText().trim());

        ObservableList<Excursion> data = FXCollections.observableList(excursionList);
        tableView.getItems().setAll(data);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            textName.setText(tableView.getSelectionModel().getSelectedItem().getName());
        });
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Excursion> excursionList = dbConnection.getExcursions(textSearch.getText().trim());

        ObservableList<Excursion> data = FXCollections.observableList(excursionList);
        tableView.getItems().setAll(data);
        labelStatus.setText("");
    }

    @FXML
    protected void handleAddAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Excursion excursion = new Excursion(textName.getText());
        if (dbConnection.addExcursion(excursion)) {
            labelStatus.setText(Localization.APP_MSG_OPERATION_ADD_SUCCESS);
            tableView.getItems().add(excursion);
            textName.setText("");
        } else {
            labelStatus.setText(Localization.APP_MSG_OPERATION_ADD_ERROR);
        }
    }

    @FXML
    private void handleEditAction() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setName(textName.getText());
            DBConnection dbConnection = new DBConnection();
            if (dbConnection.updateExcursion(tableView.getSelectionModel().getSelectedItem())) {
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
            if (dbConnection.deleteExcursion(tableView.getSelectionModel().getSelectedItem())) {
                labelStatus.setText(Localization.APP_MSG_OPERATION_DEL_SUCCESS);
                tableView.getItems().remove(selectedIndex);
            }
        }
    }

    @FXML
    public void clickMenuItemPerson(ActionEvent event) throws IOException {
        Stage stage = (Stage) textName.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Person.fxml"));
        stage.setScene(new Scene(root, Main.APP_SCENE_WIDTH, Main.APP_SCENE_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }
}
