package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentFileStream {
    public void write(List<Student> list, String file) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            for (Student s : list)
                out.writeObject(s);
        } catch (IOException e) {
            System.out.println("error opening file");
        } catch (NoSuchElementException e) {
            System.out.println("input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing file");
            }
        }
    }

    public List<Student> read(String file) {
        List<Student> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Student s = (Student) input.readObject();
                list.add(s);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
        }catch (IOException e) {
            System.out.println("error opening file.");
            System.out.println(e.getMessage());
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing file.");
            }
        }
        return list;
    }
}
