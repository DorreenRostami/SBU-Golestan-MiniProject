package Model;

import java.util.List;

public class Make {
    public void makeStudent(String username, List<Student> list){
        Student s = new Student(username);
        list.add(s);
    }
    public void makeCourse(String name, int unit, int capacity, List<Course> list){
        Course c = new Course(name, unit, capacity);
        list.add(c);
    }
    public void makeFood(String name, int price, List<Food> list){
        Food f = new Food(name, price);
        list.add(f);
    }
}
