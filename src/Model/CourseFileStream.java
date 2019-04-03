package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CourseFileStream {
    public void write(List<Course> list) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Resources/Files/courses"));
            for (Course c : list)
                out.writeObject(c);
        } catch (IOException e) {
            System.out.println("error opening course file");
        } catch (NoSuchElementException e) {
            System.out.println("course input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing course file");
            }
        }
    }

    public List<Course> read() {
        List<Course> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Resources/Files/courses"));
            while (true) {
                Course c = (Course) input.readObject();
                list.add(c);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("course class not found.");
        }catch (IOException e) {
            System.out.println("error opening course file.");
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing course file.");
            }
        }
        return list;
    }
}
