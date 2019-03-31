package Model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public static final List<Course> COURSES = new ArrayList<>();

    public int unit;
    public String name;
    public List<Student> STUDENTS_TAKING_COURSE = new ArrayList<>();
}
