package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;


public class ProfessorMakeClassController {
    private Professor signedInProfessor = (Professor) new SignedInPerson().getPerson();

    @FXML
    public Button backButton, doneButton;
    @FXML
    public TextField nameTextField, unitTextField, capacityTextField;
    @FXML
    public CheckBox firstHourCheckBox, secondHourCheckBox, thirdHourCheckBox;
    @FXML
    public CheckBox firstDayCheckBox, secondDayCheckBox, thirdDayCheckBox, fourthDayCheckBox, fifthDayCheckBox;

    private int start;
    private int end;
    private Day day;

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

    public void done() throws IOException {
        CourseFileStream cfs = new CourseFileStream();
        List<Course> list = cfs.read();
        String name = nameTextField.getText();
        int unit = Integer.valueOf(unitTextField.getText());
        int capacity = Integer.valueOf(capacityTextField.getText());
        Course newCourse = new Course(name, unit, capacity, start, end, day, signedInProfessor.getUsername());
        list.add(newCourse);
        cfs.write(list);

        List<Course> coursesGiven = signedInProfessor.getCOURSES_GIVEN();
        coursesGiven.add(newCourse);
        ProfessorFileStream pfs = new ProfessorFileStream();
        List<Professor> p = pfs.read();
        for(int i = 0; i < p.size(); i++){
            if(p.get(i).getUsername().equals(signedInProfessor.getUsername())){
                p.set(i, signedInProfessor);
                break;
            }
        }
        pfs.write(p);
        goBackToHomepage();
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
    }
}
