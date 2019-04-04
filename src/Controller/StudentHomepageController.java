package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class StudentHomepageController {
    @FXML
    public Hyperlink money, food, book, manage, back;

    public void changePage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == money)
            new PageLoader().loadScene("/View/StudentAddMoney.fxml");
    }
}
