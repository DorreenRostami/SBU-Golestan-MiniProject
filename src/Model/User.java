package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 2L;
    String username;
    String password = "123456";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        boolean change = true;
        Admin admin = new AdminFileStream().read();
        List<Student> allStudents = new StudentFileStream().read();
        List<Professor> allProfessors = new ProfessorFileStream().read();
        List<User> allUsers = new ArrayList<>(allStudents);
        allUsers.addAll(allProfessors);
        allUsers.add(admin);
        for (User u: allUsers) {
            if (u.getUsername().equals(username)) {
                change = false;
                break;
            }
        }
        if (change)
            this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() > 5)
            this.password = password;
    }
}
