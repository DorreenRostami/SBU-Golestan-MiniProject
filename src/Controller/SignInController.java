package Controller;

import Model.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class SignInController {
    @FXML
    public TextField usernameField, visiblePasswordField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button signInButton;
    @FXML
    public Label wrongLabel;
    @FXML
    public CheckBox showPassCheckBox;
    @FXML
    public Hyperlink changeHyperlink;

    public void initialize(){playTransitions();}

    public void signIn() throws IOException {
        Admin admin = new AdminFileStream().read();
        boolean signedIn = false;
        if(usernameField.getText().equals(admin.getUsername()) &&
                (passwordField.getText().equals(admin.getPassword()) || visiblePasswordField.getText().equals(admin.getPassword()))) {
            wrongLabel.setVisible(false);
            signedIn = true;
            new PageLoader().loadScene("/View/AdminHomepage.fxml");
        }
        else {
            StudentFileStream sfs = new StudentFileStream();
            List<Student> studentList = sfs.read();
            for (Student s: studentList) {
                if (usernameField.getText().equals(s.getUsername()) &&
                        (passwordField.getText().equals(s.getPassword()) || visiblePasswordField.getText().equals(s.getPassword()))) {
                    wrongLabel.setVisible(false);
                    new PageLoader().loadScene("/View/StudentHomepage.fxml");
                    signedIn = true;
                    SignedInPerson sip = new SignedInPerson();
                    sip.setPerson(s);
                    break;
                }
            }
            if(!signedIn){
                ProfessorFileStream pfs = new ProfessorFileStream();
                List <Professor> professorList = pfs.read();
                for(Professor p : professorList){
                    if (usernameField.getText().equals(p.getUsername()) &&
                            (passwordField.getText().equals(p.getPassword()) || visiblePasswordField.getText().equals(p.getPassword()))) {
                        wrongLabel.setVisible(false);
                        signedIn = true;
                        SignedInPerson sip = new SignedInPerson();
                        sip.setPerson(p);
                        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
                        break;
                    }
                }
            }
        }
        if(!signedIn)
            wrongLabel.setVisible(true);
    }

    private void playTransitions(){
        TranslateTransition trans = new TranslateTransition(Duration.millis(1000), signInButton);
        trans.setToY(15);
        trans.playFromStart();
    }

    public void showPassword() {
        if(showPassCheckBox.isSelected()){
            visiblePasswordField.setText(passwordField.getText());
            visiblePasswordField.setVisible(true);
            passwordField.setVisible(false);
        }
        else{
            passwordField.setText(visiblePasswordField.getText());
            visiblePasswordField.setVisible(false);
            passwordField.setVisible(true);
        }
    }

    public void changeUserOrPass() throws IOException {
        new PageLoader().loadScene("/View/ChangeUserAndPass.fxml");
    }
}
