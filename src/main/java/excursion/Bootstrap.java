package excursion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bootstrap extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Person.fxml"));

        stage.setTitle(Localization.APP_TITLE);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Bootstrap.class, args);
    }
}