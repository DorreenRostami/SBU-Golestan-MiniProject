package Model;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Admin {
    private Scanner scn;
    private File file = new File("Resources/Files/admin.txt");

    {
        try {
            scn = new Scanner(file, "utf-8");
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
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
            writer.write(username + "\n" + this.password);
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
            writer.write(this.username + "\n" + password);
        } catch (IOException e) {
            System.out.println("file not found");;
        }
    }
}
