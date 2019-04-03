package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class ProfessorHomepageController {
    @FXML
    public Hyperlink make, delete, students, back;


    public void makeClass(ActionEvent actionEvent) throws IOException {
        new PageLoader().loadScene("/View/ProfessorMakeClass.fxml");
    }

    public void deleteClass(ActionEvent actionEvent) {
    }

    public void goToClassesPage(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new PageLoader().loadScene("/View/SignIn.fxml");
    }
}
