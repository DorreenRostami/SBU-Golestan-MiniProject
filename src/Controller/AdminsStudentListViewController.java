package Controller;

import Model.PageLoader;
import Model.Student;
import Model.StudentFileStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class AdminsStudentListViewController {
    @FXML
    public Button backButton;
    @FXML
    public ListView<String> theStudentsNameList, theStudentsCoursesList;

    public void initialize(){
        showStudents();
    }

    private void showStudents(){
        StudentFileStream sfs = new StudentFileStream();
        List<Student> studentList = sfs.read("Resources/Files/students");
        ObservableList<String> studentNames = FXCollections.observableArrayList();
        for (int i = 0; i < studentList.size(); i++) {
            studentNames.add(studentList.get(i).getUsername());
        }
        theStudentsNameList.setItems(studentNames);
    }

    public void goBackToHomepage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton)
            new PageLoader().loadScene("/View/AdminHomePage.fxml");
    }
}