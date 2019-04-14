package pohodnik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import java.sql.SQLException;
import java.util.List;

public class UporabnikController {

    private String searchString;

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TextField textSearch;

    public UporabnikController() throws SQLException {
        System.out.println("xxx");
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Member> memberList = dbConnection.getMembers(textSearch.getText().trim());

        ObservableList<Member> data = FXCollections.observableList(memberList);
        tableView.getItems().setAll(data);
    }

}
