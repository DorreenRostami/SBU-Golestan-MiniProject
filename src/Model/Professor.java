package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable {
    private static final long serialVersionUID = 3L;
    private String username;
    private String password = "123456";
    private List<Course> COURSES_GIVEN = new ArrayList<>();

    public Professor(String username){
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

    public List<Course> getCOURSES_GIVEN() {
        return COURSES_GIVEN;
    }

    public void setCOURSES_GIVEN(List<Course> COURSES_GIVEN) {
        this.COURSES_GIVEN = COURSES_GIVEN;
    }
}
