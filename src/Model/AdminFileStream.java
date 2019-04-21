package Model;

import java.io.*;
import java.util.NoSuchElementException;

public class AdminFileStream {
    public void write(Admin admin) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Resources/Files/admin"));
            out.writeObject(admin);
        } catch (IOException e) {
            System.out.println("error opening admin file");
        } catch (NoSuchElementException e) {
            System.out.println("admin input error");
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("error closing admin file");
            }
        }
    }

    public Admin read() {
        Admin admin = null;
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Resources/Files/admin"));
            admin = (Admin) input.readObject();
        } catch (EOFException e) {
            return admin;
        } catch (ClassNotFoundException e) {
            System.out.println("admin class not found.");
        }catch (IOException e) {
            System.out.println("error opening admin file.");
        } finally {
            try {
                if(input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("error closing admin file.");
            }
        }
        return admin;
    }
}
