package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private static final long serialVersionUID = 2L;
    private String username;
    private String password = "123456";
    private long balance = 0;
    private float GPA = 20;
    private int courseCount = 0;
    private List<Course> COURSES_TAKEN = new ArrayList<>();

    public Student(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public List<Course> getCOURSES_TAKEN() {
        return COURSES_TAKEN;
    }

    public void setCOURSES_TAKEN(List<Course> COURSES_TAKEN) {
        this.COURSES_TAKEN = COURSES_TAKEN;
    }
}
