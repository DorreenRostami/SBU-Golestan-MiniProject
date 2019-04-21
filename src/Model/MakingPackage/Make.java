package Model.MakingPackage;

import Model.*;

import java.util.List;

public class Make {
    public void makeStudent(String username, List<Student> list){
        Student s = new Student(username);
        list.add(s);
    }
    public void makeCourse(String name, int unit, int capacity, int start, int end, Day day, String professor, List<Course> list){
        Course c = new Course(name, unit, capacity, start, end, day, professor);
        list.add(c);
    }
    public void makeFood(String name, int price, List<Food> list){
        Food f = new Food(name, price);
        list.add(f);
    }
    public void makeProfessor(String username, List<Professor> list){
        Professor p = new Professor(username);
        list.add(p);
    }

    public void makeBook(String title, String author, String publisher, List<Book> list){
        Book b = new Book(title, author, publisher);
        list.add(b);
    }
}
