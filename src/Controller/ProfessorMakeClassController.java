package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;


public class ProfessorMakeClassController {
    @FXML
    public Button backButton, doneButton;
    @FXML
    public TextField nameTextField, unitTextField, capacityTextField;
    @FXML
    public CheckBox firstHourCheckBox, secondHourCheckBox, thirdHourCheckBox;
    @FXML
    public CheckBox firstDayCheckBox, secondDayCheckBox, thirdDayCheckBox, fourthDayCheckBox, fifthDayCheckBox;

    private SignedInPerson signedInProfessor = new SignedInPerson();
    private int unit, capacity, start, end;
    private String name;
    private Day day;

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

    public void done(ActionEvent actionEvent) throws IOException {
        CourseFileStream cfs = new CourseFileStream();
        List<Course> list = cfs.read();
        name = nameTextField.getText();
        unit = Integer.valueOf(unitTextField.getText());
        capacity = Integer.valueOf(capacityTextField.getText());
        Course newCourse = new Course(name, unit, capacity, start, end, day, ((Professor) signedInProfessor.getPerson()).getUsername());
        list.add(newCourse);
        ((Professor) signedInProfessor.getPerson()).setCOURSES_GIVEN(list);
        cfs.write(list);
        ProfessorFileStream pfs = new ProfessorFileStream();
        List<Professor> p = pfs.read();
        for(int i = 0; i < p.size(); i++){
            if(p.get(i).getUsername().equals(((Professor) signedInProfessor.getPerson()).getUsername())){
                p.set(i, (Professor) signedInProfessor.getPerson());
                break;
            }
        }
        pfs.write(p);
        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
    }

    public void goBackToHomepage(ActionEvent actionEvent) throws IOException {
        new PageLoader().loadScene("/View/ProfessorHomepage.fxml");
    }
}
