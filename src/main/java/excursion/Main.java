package excursion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int APP_SCENE_WIDTH = 1024;
    public static final int APP_SCENE_HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Person.fxml"));

        stage.setTitle(Localization.APP_TITLE);
        stage.setScene(new Scene(root, Main.APP_SCENE_WIDTH, Main.APP_SCENE_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}