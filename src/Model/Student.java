package Model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static final List<Student> STUDENTS = new ArrayList<>();

    private String username;
    private String password;
    private long balance;
    private int GPA;
    private List<Course> COURSES_TAKEN = new ArrayList<>();

}
