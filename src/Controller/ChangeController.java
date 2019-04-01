package Controller;

import Model.Admin;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ChangeController {
    @FXML
    private Button doneButton, backButton;
    @FXML
    private TextField oldUserField, newUserField;
    @FXML
    private PasswordField oldPassField, newPassField, newPass2Field;
    @FXML
    private Label wrongLabel, takenLabel, matchLabel, blackCharLabel, redCharLabel;

    public void doneChanging(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == doneButton) {
            Admin admin = new Admin();
            if (oldUserField.getText().equals(admin.getUsername()) && oldPassField.getText().equals(admin.getPassword()))
                wrongLabel.setVisible(false);
            else
                wrongLabel.setVisible(true);
            if(!wrongLabel.isVisible()) {
                if (newUserField.getText().length() > 0 && newPassField.getText().length() == 0)
                    admin.setUsername(newUserField.getText());
                if (newPassField.getText().length() != 0 && newPassField.getText().length() < 6) {
                    blackCharLabel.setVisible(false);
                    redCharLabel.setVisible(true);
                }
                else if (newPassField.getText().length() > 5) {
                    blackCharLabel.setVisible(true);
                    redCharLabel.setVisible(false);
                    if (!newPassField.getText().equals(newPass2Field.getText()))
                        matchLabel.setVisible(true);
                    else {
                        matchLabel.setVisible(false);
                        admin.setPassword(newPassField.getText());
                        if(newUserField.getText().length() > 0)
                            admin.setUsername(newUserField.getText());
                    }
                }
            }
            if(!wrongLabel.isVisible() && !takenLabel.isVisible() && !matchLabel.isVisible() && !redCharLabel.isVisible())
                new PageLoader().loadScene("/View/SignIn.fxml");
            else {
                if(wrongLabel.isVisible()) {
                    oldUserField.setText("");
                    oldPassField.setText("");
                }
                newUserField.setText("");
                newPassField.setText("");
                newPass2Field.setText("");
            }
        }
    }

    public void goToLastPage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton)
            new PageLoader().loadScene("/View/SignIn.fxml");
    }
}
