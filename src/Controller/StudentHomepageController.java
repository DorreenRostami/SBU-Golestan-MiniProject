package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class StudentHomepageController {
    @FXML
    public Hyperlink money, food, book, manage, timetable, back;

    public void changePage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == money)
            new PageLoader().loadScene("/View/StudentAddMoney.fxml");
        else if(actionEvent.getSource() == food)
            new PageLoader().loadScene("/View/StudentFood.fxml");
        else if(actionEvent.getSource() == book)
            new PageLoader().loadScene("/View/StudentsLibrary.fxml");
        else if (actionEvent.getSource() == manage)
            new PageLoader().loadScene("/View/StudentManageClass.fxml");
        else if (actionEvent.getSource() == timetable)
            new PageLoader().loadScene("/View/StudentsTimeTable.fxml");
        else if (actionEvent.getSource() == back)
            new PageLoader().loadScene("/View/SignIn.fxml");
    }
}