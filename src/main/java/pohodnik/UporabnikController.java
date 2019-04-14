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
    private TableView<Member> tableView;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textUsername;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField actionTarget;

    public UporabnikController() {
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        System.out.println(textSearch.getText().trim());
        DBConnection dbConnection = new DBConnection();
        List<Member> memberList = dbConnection.getMembers(textSearch.getText().trim());

        ObservableList<Member> data = FXCollections.observableList(memberList);
        tableView.getItems().setAll(data);
    }

    @FXML
    protected void addAction(ActionEvent event) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Member member = new Member(textUsername.getText(), textEmail.getText(), "hidden");
        if (dbConnection.addMember(member)) {
            actionTarget.setText("Uporabnik uspešno dodan!");
        } else {
            actionTarget.setText("Napaka pri dodajanju!");
        }
    }
}
