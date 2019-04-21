package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class StudentManageClassController {
    private Student signedInStudent = (Student) new SignedInPerson().getPerson();
    private List<Course> studentsCoursesList = signedInStudent.getCOURSES_TAKEN();
    private List<Course> allCoursesList = new CourseFileStream().read();

    @FXML
    public ListView<String> classesPresentedList, classesTakenList;
    @FXML
    public TextField addClassTextField, deleteClassTextField;
    @FXML
    public Button backButton, applyChangesButton;

    public void initialize(){
        showCourses();
    }

    private void showCourses(){
        ObservableList<String> studentsCourses = FXCollections.observableArrayList();
        ObservableList<String> allCourses = FXCollections.observableArrayList();
        for (int i = 0; i < studentsCoursesList.size(); i++) {
            Course c = studentsCoursesList.get(i);
            String day = null;
            switch (c.getDay()) {
                case shanbe: day = "شنبه"; break;
                case yekshanbe: day = "یکشنبه"; break;
                case doshanbe: day = "دوشنبه"; break;
                case seshanbe: day = "سه شنبه"; break;
                case chaharshanbe: day = "چهارشنبه"; break;
            }
            studentsCourses.add((i + 1) + " - " + c.getName() + " " + c.getHour() + " " + day + " - استاد: " + c.getProfessor());
        }
        classesTakenList.setItems(studentsCourses);
        for (int i = 0; i < allCoursesList.size(); i++) {
            Course c = allCoursesList.get(i);
            String day = null;
            switch (c.getDay()) {
                case shanbe: day = "شنبه"; break;
                case yekshanbe: day = "یکشنبه"; break;
                case doshanbe: day = "دوشنبه"; break;
                case seshanbe: day = "سه شنبه"; break;
                case chaharshanbe: day = "چهارشنبه"; break;
            }
            int cap = c.getCapacity() - c.getSTUDENTS_TAKING_COURSE().size();
            allCourses.add((i + 1) + " - اسم کلاس: " + c.getName() + " ساعت: " + c.getHour() +
                    " روز: " + day + " - استاد: " + c.getProfessor() + " - ظرفیت: " + cap);
        }
        classesPresentedList.setItems(allCourses);
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/StudentHomepage.fxml");
    }

    public void done() {
        if (addClassTextField.getText().length() > 0) {
            int index = Integer.valueOf(addClassTextField.getText()) - 1;
            if (index < allCoursesList.size() && index >= 0) {
                Course newCourse = allCoursesList.get(index);
                boolean canAdd = ((signedInStudent.getCourseCount() + newCourse.getUnit() <= 20 && signedInStudent.getGPA() <= 17) ||
                        (signedInStudent.getCourseCount() + newCourse.getUnit() <= 24 && signedInStudent.getGPA() >= 17));
                if(newCourse.getCapacity() == newCourse.getSTUDENTS_TAKING_COURSE().size())
                    canAdd = false;
                if (canAdd) {
                    for (Course c : studentsCoursesList) {
                        if (c.equals(newCourse) || (c.getStart() == newCourse.getStart() && c.getDay() == newCourse.getDay())) {
                            canAdd = false;
                            break;
                        }
                    }
                }
                if (canAdd) {
                    List<String> studentsTakingCourse = newCourse.getSTUDENTS_TAKING_COURSE();
                    studentsTakingCourse.add(signedInStudent.getUsername());
                    newCourse.setSTUDENTS_TAKING_COURSE(studentsTakingCourse);
                    allCoursesList.set(index, newCourse);
                    new CourseFileStream().write(allCoursesList);

                    studentsCoursesList.add(newCourse);
                    signedInStudent.setCOURSES_TAKEN(studentsCoursesList);
                    signedInStudent.setCourseCount(signedInStudent.getCourseCount() + newCourse.getUnit());
                    List<Student> allStudents = new StudentFileStream().read();
                    for (int i = 0; i < allStudents.size(); i++) {
                        if (allStudents.get(i).getUsername().equals(signedInStudent.getUsername())) {
                            allStudents.set(i, signedInStudent);
                            break;
                        }
                    }
                    new StudentFileStream().write(allStudents);

                    List<Professor> allProfessors = new ProfessorFileStream().read();
                    outer:for (int i = 0; i < allProfessors.size(); i++) {
                        Professor prof = allProfessors.get(i);
                        if (prof.getUsername().equals(newCourse.getProfessor())) {
                            List<Course> profCourses = prof.getCOURSES_GIVEN();
                            for (int j = 0; j < profCourses.size(); j++) {
                                Course profCourse = profCourses.get(j);
                                if (profCourse.getStart() == newCourse.getStart() && profCourse.getDay() == newCourse.getDay()) {
                                    profCourses.set(j, newCourse);
                                    prof.setCOURSES_GIVEN(profCourses);
                                    allProfessors.set(i, prof);
                                    new ProfessorFileStream().write(allProfessors);
                                    break outer;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (deleteClassTextField.getText().length() > 0) {
            int index = Integer.valueOf(deleteClassTextField.getText()) - 1;
            if (index < studentsCoursesList.size() && index >= 0) {
                Course oldCourse = studentsCoursesList.get(index);
                if (oldCourse.getGrade() != null){
                    float grade = signedInStudent.getGPA() * signedInStudent.getCourseCount();
                    grade -= oldCourse.getGrade();
                    signedInStudent.setCourseCount(signedInStudent.getCourseCount() - oldCourse.getUnit());
                    if (signedInStudent.getCourseCount() == 0)
                        signedInStudent.setGPA(20);
                    else
                        signedInStudent.setGPA(grade / signedInStudent.getCourseCount());
                    oldCourse.setGrade(null);
                }
                else
                    signedInStudent.setCourseCount(signedInStudent.getCourseCount() - oldCourse.getUnit());
                List<String> studentsTakingCourse = oldCourse.getSTUDENTS_TAKING_COURSE();
                for (int i = 0; i < studentsTakingCourse.size(); i++) {
                    if (studentsTakingCourse.get(i).equals(signedInStudent.getUsername())) {
                        studentsTakingCourse.remove(i);
                        oldCourse.setSTUDENTS_TAKING_COURSE(studentsTakingCourse);
                        break;
                    }
                }
                for (int i = 0; i < allCoursesList.size(); i++) {
                    Course c = allCoursesList.get(i);
                    if (c.getName().equals(oldCourse.getName()) && c.getStart() == oldCourse.getStart() &&
                    c.getDay() == oldCourse.getDay() && c.getProfessor().equals(oldCourse.getProfessor())) {
                        allCoursesList.set(i, oldCourse);
                        break;
                    }
                }
                new CourseFileStream().write(allCoursesList);
                List<Professor> allProfessors = new ProfessorFileStream().read();
                outer:for (int i = 0; i < allProfessors.size(); i++) {
                    Professor prof = allProfessors.get(i);
                    if (prof.getUsername().equals(oldCourse.getProfessor())) {
                        List<Course> profCourses = prof.getCOURSES_GIVEN();
                        for (int j = 0; j < profCourses.size(); j++) {
                            Course profCourse = profCourses.get(j);
                            if (profCourse.getStart() == oldCourse.getStart() && profCourse.getDay() == oldCourse.getDay()) {
                                profCourses.set(j, oldCourse);
                                prof.setCOURSES_GIVEN(profCourses);
                                allProfessors.set(i, prof);
                                new ProfessorFileStream().write(allProfessors);
                                break outer;
                            }
                        }
                    }
                }
                studentsCoursesList.remove(index);
                signedInStudent.setCOURSES_TAKEN(studentsCoursesList);
                List<Student> allStudents = new StudentFileStream().read();
                for (int i = 0; i < allStudents.size(); i++) {
                    if (allStudents.get(i).getUsername().equals(signedInStudent.getUsername())) {
                        allStudents.set(i, signedInStudent);
                        break;
                    }
                }
                new StudentFileStream().write(allStudents);
            }
        }
        showCourses();
        deleteClassTextField.setText("");
        addClassTextField.setText("");
    }
}
