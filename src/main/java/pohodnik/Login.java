package pohodnik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        stage.setTitle("Prijavno okno");
        stage.setScene(new Scene(root, 400, 350));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Login.class, args);
    }
}