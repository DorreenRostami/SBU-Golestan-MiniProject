package Controller;

import Model.Course;
import Model.PageLoader;
import Model.Professor;
import Model.ProfessorFileStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AdminsProfessorListViewController {
    private List<Professor> allProfessors = new ProfessorFileStream().read();

    @FXML
    public ListView<String> theProfessorsList, theCoursesList, theStudentsList;
    @FXML
    public Button backButton, okButton;
    @FXML
    public TextField professorTextField;
    @FXML
    public Text thePass;

    public void initialize() {
        showProfessors();
    }

    private void showProfessors() {
        ObservableList<String> professorNames = FXCollections.observableArrayList();
        for (Professor p : allProfessors)
            professorNames.add(p.getUsername());
        theProfessorsList.setItems(professorNames);
    }

    public void showProfessorDetails() {
        String professorName = professorTextField.getText();
        Professor professor = null;
        for (Professor p : allProfessors) {
            if (p.getUsername().equals(professorName)) {
                professor = p;
                break;
            }
        }
        if (professor != null) {
            ObservableList<String> coursesGiven = FXCollections.observableArrayList();
            ObservableList<String> students = FXCollections.observableArrayList();
            thePass.setText(professor.getPassword());
            thePass.setVisible(true);
            for (Course c : professor.getCOURSES_GIVEN()) {
                String day = null;
                switch (c.getDay()) {
                    case shanbe:
                        day = "شنبه";
                        break;
                    case yekshanbe:
                        day = "یکشنبه";
                        break;
                    case doshanbe:
                        day = "دوشنبه";
                        break;
                    case seshanbe:
                        day = "سه شنبه";
                        break;
                    case chaharshanbe:
                        day = "چهارشنبه";
                        break;
                }
                coursesGiven.add("اسم کلاس: " + c.getName() + " / واحد: " + c.getUnit() +
                        " / ساعت: " + c.getHour() + " / روز: " + day);
                for (String s : c.getSTUDENTS_TAKING_COURSE())
                    students.add("نام دانشجو: " + s + " / اسم کلاس: " + c.getName());
            }
            theCoursesList.setItems(coursesGiven);
            theStudentsList.setItems(students);
        }
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/AdminHomepage.fxml");
    }
}

