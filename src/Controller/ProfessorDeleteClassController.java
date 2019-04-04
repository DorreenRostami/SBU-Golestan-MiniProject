package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.List;

public class ProfessorDeleteClassController {
    private SignedInPerson signedInPerson = new SignedInPerson();
    private Course currCourse = null;
    private int start, end;
    private Day day;

    @FXML
    public TextField nameTextField;
    @FXML
    public Button showCourseButton, doneButton;
    @FXML
    public ListView<String> theStudentsNamesList;
    @FXML
    public Text theUnit, theCapacity, wrongClass;
    @FXML
    public CheckBox firstHourCheckBox, secondHourCheckBox, thirdHourCheckBox;
    @FXML
    public CheckBox firstDayCheckBox, secondDayCheckBox, thirdDayCheckBox, fourthDayCheckBox, fifthDayCheckBox;

    public void showCourse(){
        String name = nameTextField.getText();
        Professor professor = (Professor) signedInPerson.getPerson();
        List<Course> courses = professor.getCOURSES_GIVEN();
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

    public void done(){
        if(!wrongClass.isVisible()) {
            Professor professor = (Professor) signedInPerson.getPerson();
            List<Course> courses = professor.getCOURSES_GIVEN();
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).equals(currCourse)) {
                    courses.remove(i);
                    break;
                }
            }
            professor.setCOURSES_GIVEN(courses);
            ProfessorFileStream pfs = new ProfessorFileStream();
            List<Professor> professorList = pfs.read();
            for (int i = 0; i < professorList.size(); i++) {
                if (professorList.get(i).getUsername().equals(professor.getUsername())) {
                    professorList.set(i, professor);
                    break;
                }
            }
            pfs.write(professorList);

            CourseFileStream cfs = new CourseFileStream();
            List<Course> courseList = cfs.read();
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get(i).equals(currCourse)) {
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
                    if (studentsCourses.get(i).equals(currCourse)) {
                        studentsCourses.remove(i);
                        studentList.get(i).setCOURSES_TAKEN(studentsCourses);
                        break;
                    }
                }
            }
            sfs.write(studentList);
        }
    }

    public void firstHour(ActionEvent actionEvent) {
        if(firstHourCheckBox.isSelected()){
            start = 8;
            end = 10;
            if(secondHourCheckBox.isSelected())
                secondHourCheckBox.setSelected(false);
            else if(thirdHourCheckBox.isSelected())
                thirdHourCheckBox.setSelected(false);
        }
    }

    public void secondHour(ActionEvent actionEvent) {
        if(secondHourCheckBox.isSelected()){
            start = 10;
            end = 12;
            if(firstHourCheckBox.isSelected())
                firstHourCheckBox.setSelected(false);
            else if(thirdHourCheckBox.isSelected())
                thirdHourCheckBox.setSelected(false);
        }
    }

    public void thirdHour(ActionEvent actionEvent) {
        if(thirdHourCheckBox.isSelected()){
            start = 14;
            end = 16;
            if(secondHourCheckBox.isSelected())
                secondHourCheckBox.setSelected(false);
            else if(firstHourCheckBox.isSelected())
                firstHourCheckBox.setSelected(false);
        }
    }

    public void firstDay(ActionEvent actionEvent) {
        day = Day.shanbe;
        if(secondDayCheckBox.isSelected())
            secondDayCheckBox.setSelected(false);
        else if(thirdDayCheckBox.isSelected())
            thirdDayCheckBox.setSelected(false);
        else if(fourthDayCheckBox.isSelected())
            fourthDayCheckBox.setSelected(false);
        else if(fifthDayCheckBox.isSelected())
            fifthDayCheckBox.setSelected(false);
    }

    public void secondDay(ActionEvent actionEvent) {
        day = Day.yekshanbe;
        if(firstDayCheckBox.isSelected())
            firstDayCheckBox.setSelected(false);
        else if(thirdDayCheckBox.isSelected())
            thirdDayCheckBox.setSelected(false);
        else if(fourthDayCheckBox.isSelected())
            fourthDayCheckBox.setSelected(false);
        else if(fifthDayCheckBox.isSelected())
            fifthDayCheckBox.setSelected(false);
    }

    public void thirdDay(ActionEvent actionEvent) {
        day = Day.doshanbe;
        if(secondDayCheckBox.isSelected())
            secondDayCheckBox.setSelected(false);
        else if(firstDayCheckBox.isSelected())
            firstDayCheckBox.setSelected(false);
        else if(fourthDayCheckBox.isSelected())
            fourthDayCheckBox.setSelected(false);
        else if(fifthDayCheckBox.isSelected())
            fifthDayCheckBox.setSelected(false);
    }

    public void fourthDay(ActionEvent actionEvent) {
        day = Day.seshanbe;
        if(secondDayCheckBox.isSelected())
            secondDayCheckBox.setSelected(false);
        else if(thirdDayCheckBox.isSelected())
            thirdDayCheckBox.setSelected(false);
        else if(firstDayCheckBox.isSelected())
            firstDayCheckBox.setSelected(false);
        else if(fifthDayCheckBox.isSelected())
            fifthDayCheckBox.setSelected(false);
    }

    public void fifthDay(ActionEvent actionEvent) {
        day = Day.chaharshanbe;
        if(secondDayCheckBox.isSelected())
            secondDayCheckBox.setSelected(false);
        else if(thirdDayCheckBox.isSelected())
            thirdDayCheckBox.setSelected(false);
        else if(fourthDayCheckBox.isSelected())
            fourthDayCheckBox.setSelected(false);
        else if(firstDayCheckBox.isSelected())
            firstDayCheckBox.setSelected(false);
    }
}
