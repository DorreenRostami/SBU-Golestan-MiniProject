package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AdminHomepageController {
    @FXML
    public Hyperlink foods, books, students, professors, classes, back;

    public void changePage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == foods)
            new PageLoader().loadScene("/View/AdminFoodSelecting.fxml");
        else if(actionEvent.getSource() == students)
            new PageLoader().loadScene("/View/AdminsStudentListView.fxml");
        else if(actionEvent.getSource() == professors)
            new PageLoader().loadScene("/View/AdminsProfessorListView.fxml");
        else if(actionEvent.getSource() == classes)
            new PageLoader().loadScene("/View/AdminsCourseListView.fxml");
        else if(actionEvent.getSource() == books)
            new PageLoader().loadScene("/View/AdminsLibrary.fxml");
        else if(actionEvent.getSource() == back)
            new PageLoader().loadScene("/View/SignIn.fxml");

    }
}
