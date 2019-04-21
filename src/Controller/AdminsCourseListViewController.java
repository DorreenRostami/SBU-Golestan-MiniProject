package Controller;

import Model.Course;
import Model.CourseFileStream;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class AdminsCourseListViewController {
    @FXML
    public ListView<String> coursesList;
    @FXML
    public Button backButton;

    public void initialize(){
        showCourses();
    }

    private void showCourses() {
        List<Course> courseList = new CourseFileStream().read();
        ObservableList<String> courses = FXCollections.observableArrayList();
        for (Course c : courseList) {
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
            courses.add("اسم کلاس: " + c.getName() + " / واحد: " + c.getUnit() +
                    " / ساعت: " + c.getHour() + " / روز: " + day + " / استاد: " + c.getProfessor());
        }
        coursesList.setItems(courses);
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/AdminHomepage.fxml");
    }
}
