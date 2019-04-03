package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private int unit, capacity, start, end;
    private String name, professor;
    private List<String> STUDENTS_TAKING_COURSE = new ArrayList<>();
    private Day day;

    public Course(String name, int unit, int capacity, int start, int end, Day day, String professor){
        this.name = name;
        this.unit = unit;
        this.capacity = capacity;
        this.start = start;
        this.end = end;
        this.day = day;
        this.professor = professor;
    }

    public int getUnit() {
        return unit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public List<String> getSTUDENTS_TAKING_COURSE() {
        return STUDENTS_TAKING_COURSE;
    }

    public void setSTUDENTS_TAKING_COURSE(List<String> STUDENTS_TAKING_COURSE) {
        this.STUDENTS_TAKING_COURSE = STUDENTS_TAKING_COURSE;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Day getDay() {
        return day;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
