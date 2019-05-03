package pohodnik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.List;

public class UporabnikController {

    private String searchString;

    @FXML
    private TableView<Oseba> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textIme;

    @FXML
    private TextField textPriimek;

    @FXML
    private TextField actionTarget;

    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            textIme.setText(tableView.getSelectionModel().getSelectedItem().getIme());
            textPriimek.setText(tableView.getSelectionModel().getSelectedItem().getPriimek());

            System.out.println(tableView.getSelectionModel().getSelectedItem().getId());

        });
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Oseba> osebaList = dbConnection.getMembers(textSearch.getText().trim());

        ObservableList<Oseba> data = FXCollections.observableList(osebaList);
        tableView.getItems().setAll(data);
    }

    @FXML
    protected void addAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Oseba oseba = new Oseba(textIme.getText(), textPriimek.getText());
        if (dbConnection.addMember(oseba)) {
            actionTarget.setText("Uporabnik uspešno dodan!");
        } else {
            actionTarget.setText("Napaka pri dodajanju!");
        }
    }

    @FXML
    private void handleEditPerson() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getSelectionModel().getSelectedItem().setIme(textIme.getText());
            tableView.getSelectionModel().getSelectedItem().setPriimek(textPriimek.getText());
            DBConnection dbConnection = new DBConnection();
            dbConnection.updateMember(tableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            DBConnection dbConnection = new DBConnection();
            dbConnection.deleteMember(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(selectedIndex);
        }
    }
}
