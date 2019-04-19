package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class ProfessorHomepageController {
    @FXML
    public Hyperlink make, delete, students, back;

    public void changePage(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == make)
            new PageLoader().loadScene("/View/ProfessorMakeClass.fxml");
        else if (actionEvent.getSource() == delete)
            new PageLoader().loadScene("/View/ProfessorDeleteClass.fxml");
        else if (actionEvent.getSource() == back)
            new PageLoader().loadScene("/View/SignIn.fxml");
        else if (actionEvent.getSource() == students)
            new PageLoader().loadScene("/View/ProfessorsStudents.fxml");
    }
}
