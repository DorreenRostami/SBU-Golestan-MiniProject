package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class ProfessorsStudentsController {
    private Professor signedInProfessor = (Professor) new SignedInPerson().getPerson();
    private int start, end;
    private Day day;

    @FXML
    public ListView<String> studentList;
    @FXML
    public TextField classNameTextField, studentNameTextField, gradeTextField;
    @FXML
    public CheckBox firstHourCheckBox, secondHourCheckBox, thirdHourCheckBox;
    @FXML
    public CheckBox firstDayCheckBox, secondDayCheckBox, thirdDayCheckBox, fourthDayCheckBox, fifthDayCheckBox;
    @FXML
    public Button doneButton, addGradeButton, backButton;
    @FXML
    public Text wrongText;

    public void initialize(){
        showCourses();
    }

    private void showCourses(){
        List<Course> courseList = signedInProfessor.getCOURSES_GIVEN();
        ObservableList<String> courses = FXCollections.observableArrayList();
        for (Course c : courseList) {
            String day = null;
            switch (c.getDay()) {
                case shanbe: day = "شنبه"; break;
                case yekshanbe: day = "یکشنبه"; break;
                case doshanbe: day = "دوشنبه"; break;
                case seshanbe: day = "سه شنبه"; break;
                case chaharshanbe: day = "چهارشنبه"; break;
            }
            List<String> stc = c.getSTUDENTS_TAKING_COURSE();
            for (String s : stc)
                courses.add(s + " : " + c.getName() + " " + c.getStart() + " - " + c.getEnd() + " " + day);
        }
        studentList.setItems(courses);
    }

    public void selectHour(ActionEvent actionEvent) {
        firstHourCheckBox.setSelected(false);
        secondHourCheckBox.setSelected(false);
        thirdHourCheckBox.setSelected(false);
        if(actionEvent.getSource() == firstHourCheckBox){
            start = 8;
            end = 10;
            firstHourCheckBox.setSelected(true);
        }
        else if(actionEvent.getSource() == secondHourCheckBox){
            start = 10;
            end = 12;
            secondHourCheckBox.setSelected(true);
        }
        else if(actionEvent.getSource() == thirdHourCheckBox){
            start = 14;
            end = 16;
            thirdHourCheckBox.setSelected(true);
        }
    }

    public void selectDay(ActionEvent actionEvent) {
        firstDayCheckBox.setSelected(false);
        secondDayCheckBox.setSelected(false);
        thirdDayCheckBox.setSelected(false);
        fourthDayCheckBox.setSelected(false);
        fifthDayCheckBox.setSelected(false);
        if (actionEvent.getSource() == firstDayCheckBox) {
            day = Day.shanbe;
            firstDayCheckBox.setSelected(true);
        }
        else if (actionEvent.getSource() == secondDayCheckBox) {
            day = Day.yekshanbe;
            secondDayCheckBox.setSelected(true);
        }
        else if (actionEvent.getSource() == thirdDayCheckBox) {
            day = Day.doshanbe;
            thirdDayCheckBox.setSelected(true);
        }
        else if (actionEvent.getSource() == fourthDayCheckBox) {
            day = Day.seshanbe;
            fourthDayCheckBox.setSelected(true);
        }
        else if (actionEvent.getSource() == fifthDayCheckBox) {
            day = Day.chaharshanbe;
            fifthDayCheckBox.setSelected(true);
        }
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
    }

    public void done(ActionEvent actionEvent) {
        List<Course> courseList = signedInProfessor.getCOURSES_GIVEN();
        List<Student> allStudents = new StudentFileStream().read();
        outer:for (Course c : courseList) {
            if (c.getName().equals(classNameTextField.getText()) && c.getStart() == start && c.getEnd() == end && day == c.getDay()){
                wrongText.setVisible(false);
                List<String> students = c.getSTUDENTS_TAKING_COURSE();
                for (String name : students){
                    if (name.equals(studentNameTextField.getText())){
                        float grade = Float.valueOf(gradeTextField.getText());
                        if (grade <= 20 && grade >= 0){
                            for (int i = 0; i < allStudents.size(); i++){
                                Student s = allStudents.get(i);
                                if (s.getUsername().equals(studentNameTextField.getText())){
                                    List<Course> coursesTaken = s.getCOURSES_TAKEN();
                                    for (Course ct: coursesTaken)
                                        if (ct.getName().equals(classNameTextField.getText())) {
                                            ct.setGrade(grade);
                                            float GPA = s.getGPA() * s.getCourseCount();
                                            GPA += grade - 20;
                                            GPA /= s.getCourseCount();
                                            s.setGPA(GPA);
                                            allStudents.set(i, s);
                                            new StudentFileStream().write(allStudents);
                                            break outer;
                                        }
                                }
                            }
                        }
                        else {
                            wrongText.setVisible(true);
                            break outer;
                        }
                    }
                    else
                        wrongText.setVisible(true);
                }
            }
            else
                wrongText.setVisible(true);
        }
        if(actionEvent.getSource() == doneButton && !wrongText.isVisible()) {
            try {
                goBackToHomepage();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}