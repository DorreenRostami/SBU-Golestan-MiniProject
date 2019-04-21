package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeController {
    private SignedInPerson changing = new SignedInPerson();
    private Admin admin = new AdminFileStream().read();
    private List<Student> allStudents = new StudentFileStream().read();
    private List<Professor> allProfessors = new ProfessorFileStream().read();

    @FXML
    public Button doneButton, backButton;
    @FXML
    public TextField oldUserField, newUserField;
    @FXML
    public PasswordField oldPassField, newPassField, newPass2Field;
    @FXML
    public Label wrongLabel, takenLabel, matchLabel, blackCharLabel, redCharLabel;

    public void doneChanging() throws IOException {
        wrongLabel.setVisible(true);
        List<User> allUsers = new ArrayList<>(allStudents);
        allUsers.addAll(allProfessors);
        allUsers.add(admin);

        for (User u: allUsers) {
            if (oldUserField.getText().equals(u.getUsername()) && oldPassField.getText().equals(u.getPassword())) {
                wrongLabel.setVisible(false);
                changing.setPerson(u);
                changeUser();
                break;
            }
        }
        if (!wrongLabel.isVisible() && !takenLabel.isVisible() && !matchLabel.isVisible() && !redCharLabel.isVisible()) {
            if (changing.getPerson() instanceof Admin) {
                new AdminFileStream().write((Admin) changing.getPerson());
            }
            else if (changing.getPerson() instanceof Student) {
                new StudentFileStream().write(allStudents);
            }
            else if (changing.getPerson() instanceof Professor) {
                new ProfessorFileStream().write(allProfessors);
            }
            new PageLoader().loadScene("/View/SignIn.fxml");
        }
        else {
            if (wrongLabel.isVisible()) {
                oldUserField.setText("");
                oldPassField.setText("");
            }
            newUserField.setText("");
            newPassField.setText("");
            newPass2Field.setText("");
        }
    }

    private void changeUser() {
        if (newUserField.getText().length() > 0) {
            User u = changing.getPerson();
            u.setUsername(newUserField.getText());
            changing.setPerson(u);
            if (changing.getPerson().getUsername().equals(oldUserField.getText()))
                takenLabel.setVisible(true);
            else
                takenLabel.setVisible(false);
        }
        else
            takenLabel.setVisible(false);
        if (newPassField.getText().length() > 0) {
            if (newPassField.getText().equals(newPass2Field.getText()))
                matchLabel.setVisible(false);
            else
                matchLabel.setVisible(true);
            if (!matchLabel.isVisible()) {
                User user = changing.getPerson();
                user.setPassword(newPassField.getText());
                changing.setPerson(user);
                if (user.getPassword().equals(oldPassField.getText())) {
                    blackCharLabel.setVisible(false);
                    redCharLabel.setVisible(true);
                }
                else {
                    blackCharLabel.setVisible(true);
                    redCharLabel.setVisible(false);
                }
            }
        }
        else {
            matchLabel.setVisible(false);
            blackCharLabel.setVisible(true);
            redCharLabel.setVisible(false);
        }
    }

    public void goToLastPage() throws IOException {
        new PageLoader().loadScene("/View/SignIn.fxml");
    }
}