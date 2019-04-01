package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FoodFileStream {
    public void write(List<Food> list, String file) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            for (Food f : list)
                out.writeObject(f);
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

    public List<Food> read(String file) {
        List<Food> list = new ArrayList<>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Food f = (Food) input.readObject();
                list.add(f);
            }
        } catch (EOFException e) {
            return list;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
        }catch (IOException e) {
            System.out.println("error opening file." + e.toString());
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
