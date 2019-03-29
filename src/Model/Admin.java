package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Admin {
    private Scanner scn;

    {
        try {
            scn = new Scanner(new File("Resources/Files/admin.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    private String username = scn.nextLine();
    private String password = scn.nextLine();


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
}
