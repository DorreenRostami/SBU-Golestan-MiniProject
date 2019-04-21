package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class StudentsTimeTableController {
    private Student signedInStudent = (Student) new SignedInPerson().getPerson();
    @FXML
    public TableView<Course> timetable;
    @FXML
    public TableColumn<Course, String> dayCol, hourCol, nameCol, profCol;

    public void initialize() {
        show();
    }

    private void show() {
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        profCol.setCellValueFactory(new PropertyValueFactory<>("professor"));

        List<Course> studentsCourses = signedInStudent.getCOURSES_TAKEN();
        ObservableList<Course> observableList = FXCollections.observableArrayList();
        observableList.addAll(studentsCourses);
        timetable.setItems(observableList);
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/StudentHomepage.fxml");
    }
}
