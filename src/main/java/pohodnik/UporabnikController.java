package pohodnik;
//https://stackoverflow.com/questions/44735486/javafx-scenebuilder-search-listview
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UporabnikController {
    protected String searchString;

    private ObservableList<String> list = FXCollections.observableArrayList();
    @FXML private ListView<String> lstArtiest;

    @FXML private TextField textSearch;

    public UporabnikController() {
    }

    @FXML
    void inputField(InputEvent event) {
        searchString = textSearch.getText();
        loadList(searchString);
    }


    @FXML
    public void initialize() {

        loadList(Query);

    }



    public void loadList(String Query) {




    }
}
