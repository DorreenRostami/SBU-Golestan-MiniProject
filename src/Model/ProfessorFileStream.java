package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProfessorFileStream {
    public void write(List<Professor> list) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Resources/Files/professors"));
            for (Professor p : list)
                out.writeObject(p);
        } catch (IOException e) {
            System.out.println("error opening professor file");
        } catch (NoSuchElementException e) {
            System.out.println("professor input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing professor file");
            }
        }
    }

    public List<Professor> read() {
        List<Professor> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Resources/Files/professors"));
            while (true) {
                Professor p = (Professor) input.readObject();
                list.add(p);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("professor class not found.");
        }catch (IOException e) {
            System.out.println("error opening professor file.");
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing professor file.");
            }
        }
        return list;
    }
}
