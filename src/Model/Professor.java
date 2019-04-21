package Model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private List<Course> COURSES_GIVEN = new ArrayList<>();

    public Professor(String username){
        this.username = username;
    }

    public List<Course> getCOURSES_GIVEN() {
        return COURSES_GIVEN;
    }

    public void setCOURSES_GIVEN(List<Course> COURSES_GIVEN) {
        this.COURSES_GIVEN = COURSES_GIVEN;
    }
}
