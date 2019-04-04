package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentFileStream {
    public void write(List<Student> list) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Resources/Files/students"));
            for (Student s : list)
                out.writeObject(s);
        } catch (IOException e) {
            System.out.println("error opening student file");
        } catch (NoSuchElementException e) {
            System.out.println("student input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing student file");
            }
        }
    }

    public List<Student> read() {
        List<Student> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Resources/Files/students"));
            while (true) {
                Student s = (Student) input.readObject();
                list.add(s);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("student class not found.");
        }catch (IOException e) {
            System.out.println("error opening student file. ");
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing student file.");
            }
        }
        return list;
    }
}
