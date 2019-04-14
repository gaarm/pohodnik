package pohodnik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.List;

public class UporabnikController {
    protected String searchString;

    @FXML
    private ListView<Member> listView;

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

        listView.setItems(data);

        listView.setCellFactory(new Callback<ListView<Member>, ListCell<Member>>(){

            @Override
            public ListCell<Member> call(ListView<Member> p) {

                ListCell<Member> cell = new ListCell<Member>(){

                    @Override
                    protected void updateItem(Member t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getUsername() + " " + t.getEmail());
                        }
                    }

                };

                return cell;
            }
        });

    }

    @FXML
    void inputField(InputEvent event) {
        searchString = textSearch.getText();
        loadList(searchString);
    }


    @FXML
    public void initialize() {
        loadList(searchString);
    }

    public void loadList(String searchString) {


    }
}
