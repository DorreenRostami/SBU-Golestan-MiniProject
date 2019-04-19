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

public class ProfessorDeleteClassController {
    private Professor signedInProfessor = (Professor) new SignedInPerson().getPerson();
    private Course currCourse = null;
    private int start, end;
    private Day day;

    @FXML
    public TextField nameTextField;
    @FXML
    public Button showCourseButton, doneButton;
    @FXML
    public Text theUnit, theCapacity, wrongClass;
    @FXML
    public CheckBox firstHourCheckBox, secondHourCheckBox, thirdHourCheckBox;
    @FXML
    public CheckBox firstDayCheckBox, secondDayCheckBox, thirdDayCheckBox, fourthDayCheckBox, fifthDayCheckBox;
    @FXML
    public ListView<String> theStudentsNamesList, theCoursesList;

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
            courses.add(c.getName() + " " + c.getStart() + " - " + c.getEnd() + " " + day);
        }
        theCoursesList.setItems(courses);
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

    public void showCourse(){
        String name = nameTextField.getText();
        List<Course> courses = signedInProfessor.getCOURSES_GIVEN();
        for(Course c : courses){
            if(name.equals(c.getName()) && c.getStart() == start && c.getEnd() == end && c.getDay().equals(day)){
                currCourse = c;
                wrongClass.setVisible(false);
                break;
            }
            else
                wrongClass.setVisible(true);
        }
        if(!wrongClass.isVisible()) {
            theUnit.setText(Integer.toString(currCourse.getUnit()));
            theCapacity.setText(Integer.toString(currCourse.getCapacity()));
            theUnit.setVisible(true);
            theCapacity.setVisible(true);
            List<String> studentList = currCourse.getSTUDENTS_TAKING_COURSE();
            ObservableList<String> studentNames = FXCollections.observableArrayList();
            studentNames.addAll(studentList);
            theStudentsNamesList.setItems(studentNames);
        }
    }

    public void done() throws IOException {
        showCourse();
        if(!wrongClass.isVisible()) {
            List<Course> courses = signedInProfessor.getCOURSES_GIVEN();
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).equals(currCourse)) {
                    courses.remove(i);
                    break;
                }
            }
            signedInProfessor.setCOURSES_GIVEN(courses);
            ProfessorFileStream pfs = new ProfessorFileStream();
            List<Professor> professorList = pfs.read();
            for (int i = 0; i < professorList.size(); i++) {
                if (professorList.get(i).getUsername().equals(signedInProfessor.getUsername())) {
                    professorList.set(i, signedInProfessor);
                    break;
                }
            }
            pfs.write(professorList);

            CourseFileStream cfs = new CourseFileStream();
            List<Course> courseList = cfs.read();
            for (int i = 0; i < courseList.size(); i++) {
                Course c = courseList.get(i);
                if (c.getName().equals(currCourse.getName()) && c.getProfessor().equals(signedInProfessor.getUsername()) &&
                        c.getStart() == currCourse.getStart() && c.getDay() == currCourse.getDay()) {
                    courseList.remove(i);
                    break;
                }
            }
            cfs.write(courseList);

            StudentFileStream sfs = new StudentFileStream();
            List<Student> studentList = sfs.read();
            for (int i = 0; i < studentList.size(); i++) {
                List<Course> studentsCourses = studentList.get(i).getCOURSES_TAKEN();
                for (int j = 0; j < studentsCourses.size(); j++) {
                    Course c = studentsCourses.get(i);
                    if (c.getName().equals(currCourse.getName()) && c.getProfessor().equals(signedInProfessor.getUsername()) &&
                            c.getStart() == currCourse.getStart() && c.getDay() == currCourse.getDay()) {
                        studentsCourses.remove(i);
                        studentList.get(i).setCOURSES_TAKEN(studentsCourses);
                        break;
                    }
                }
            }
            sfs.write(studentList);

            new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
        }
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
    }
}
