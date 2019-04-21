package Model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private long balance = 0;
    private float GPA = 20;
    private int courseCount = 0;
    private List<Course> COURSES_TAKEN = new ArrayList<>();
    private List<Book> RESERVED_BOOKS = new ArrayList<>();
    private List<Food> RESERVED_FOODS = new ArrayList<>();

    public Student(String username){
        this.username = username;
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

    public void setGPA(float GPA) {
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

    public List<Book> getRESERVED_BOOKS() {
        return RESERVED_BOOKS;
    }

    public void setRESERVED_BOOKS(List<Book> RESERVED_BOOKS) {
        this.RESERVED_BOOKS = RESERVED_BOOKS;
    }

    public List<Food> getRESERVED_FOODS() {
        return RESERVED_FOODS;
    }

    public void setRESERVED_FOODS(List<Food> RESERVED_FOODS) {
        this.RESERVED_FOODS = RESERVED_FOODS;
    }
}
