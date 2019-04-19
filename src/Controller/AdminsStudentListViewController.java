package Controller;

import Model.Course;
import Model.PageLoader;
import Model.Student;
import Model.StudentFileStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AdminsStudentListViewController {
    private List<Student> studentList = new StudentFileStream().read();

    @FXML
    public Button backButton, okButton;
    @FXML
    public ListView<String> theStudentsNamesList, theStudentsCoursesList;
    @FXML
    public Text thePass, theBalance, theGPA;
    @FXML
    public TextField studentTextField;

    public void initialize(){
        showStudents();
    }

    private void showStudents(){
        ObservableList<String> studentNames = FXCollections.observableArrayList();
        for (Student student : studentList)
            studentNames.add(student.getUsername());
        theStudentsNamesList.setItems(studentNames);
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/AdminHomepage.fxml");
    }

    public void showStudentDetails() {
        String name = studentTextField.getText();
        Student student = null;
        for (Student s : studentList) {
            if (s.getUsername().equals(name)){
                student = s;
                break;
            }
        }
        if(student != null) {
            ObservableList<String> studentCourses = FXCollections.observableArrayList();
            thePass.setText(student.getPassword());
            theBalance.setText(Long.toString(student.getBalance()));
            theGPA.setText(Float.toString(student.getGPA()));
            thePass.setVisible(true);
            theBalance.setVisible(true);
            theGPA.setVisible(true);
            for (Course c : student.getCOURSES_TAKEN()) {
                studentCourses.add("اسم کلاس: " + c.getName() + " / واحد: " + c.getUnit() +
                        " / استاد: " + c.getProfessor() + " / نمره: " + (c.getGrade() == null ? "-" : c.getGrade()));
            }
            theStudentsCoursesList.setItems(studentCourses);
        }
    }
}