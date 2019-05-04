package myapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private Text actiontarget;
    @FXML private TextField usernameField ;
    @FXML private TextField passwordField ;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        DBConnection dbConnection = new DBConnection();

        if (!dbConnection.canLogin(usernameField.getText().trim(), passwordField.getText().trim())) {
            actiontarget.setText("Prijava uspela");
            closeLoginWindow();


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Person.fxml"));
            stage.setScene(new Scene(root));

            //Fill stage with content
            stage.show();
        } else {
            actiontarget.setText("Prijava ni uspela!");
        }
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
