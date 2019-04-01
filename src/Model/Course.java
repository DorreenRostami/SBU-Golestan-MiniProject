package Model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    int unit;
    int capacity;
    String name;
    List<Student> STUDENTS_TAKING_COURSE = new ArrayList<>();
    public Course(String name, int unit, int capacity){
        this.name = name;
        this.unit = unit;
        this.capacity = capacity;
    }
}
