package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookFileStream {
    public void write(List<Book> list) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Resources/Files/books"));
            for (Book b : list)
                out.writeObject(b);
        } catch (IOException e) {
            System.out.println("error opening book file");
        } catch (NoSuchElementException e) {
            System.out.println("book input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing book file");
            }
        }
    }

    public List<Book> read() {
        List<Book> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Resources/Files/books"));
            while (true) {
                Book b = (Book) input.readObject();
                list.add(b);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("book class not found.");
        }catch (IOException e) {
            System.out.println("error opening book file.");
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing book file.");
            }
        }
        return list;
    }
}
