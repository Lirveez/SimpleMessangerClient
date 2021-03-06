package org.matmed.messengerclient.client.app.registration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FirstPageController extends PageController {
    private static final String FILE_NAME = "FirstPageForm.fxml";
    @FXML
    private TextField login, firstName, lastName, email, password1, password2;
    @FXML
    private Label errorLabel;

    @FXML
    private void onCancel()
    {
        RegistrationController.cancel();
    }
    @FXML
    private void onNext()
    {
        if (password1.getText().equals(password2.getText())) {
            RegistrationController.verify(login.getText(), password1.getText(),
                    firstName.getText()+" "+lastName.getText(), email.getText(), null);
        }
        else
        {
            displayMessage("Пароли не совпадают");
        }
    }
    @Override
    public void displayMessage(String text)
    {
        errorLabel.setText(text);
    }


    public static FirstPageController create() throws IOException {
        FXMLLoader loader = new FXMLLoader(FirstPageController.class.getResource(FILE_NAME));
        loader.load();
        return loader.getController();
    }
}
