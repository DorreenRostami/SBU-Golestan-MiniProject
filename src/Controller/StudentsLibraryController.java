package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsLibraryController {
    private Student signedInStudent = (Student) new SignedInPerson().getPerson();
    private List<Book> allBooks = new BookFileStream().read();
    private List<Book> availableBooks = new ArrayList<>();
    private List<Book> reservedBooks = new ArrayList<>();

    @FXML
    public Button backButton, reserveButton, giveBackButton;
    @FXML
    public ListView<String> availableBooksList, reservedBooksList;
    @FXML
    public TextField bookNumberTextField1, bookNumberTextField2;

    public void initialize() {
        showBooks();
    }

    private void showBooks() {
        ObservableList<String> availableBooksString = FXCollections.observableArrayList();
        ObservableList<String> reservedBooksString = FXCollections.observableArrayList();
        availableBooks.clear();
        reservedBooks.clear();
        for (Book b : allBooks) {
            if (!b.isReserved() && b.isConfirmed()) {
                availableBooksString.add((availableBooks.size() + 1) + "- نام کتاب: " + b.getTitle() + " نام نویسنده: " + b.getAuthor() + " ناشر: " + b.getPublisher());
                availableBooks.add(b);
            }
            else if (b.isReserved()) {
                List<Book> studentsBooks = signedInStudent.getRESERVED_BOOKS();
                for (Book studentsBook : studentsBooks)
                    if (studentsBook.getTitle().equals(b.getTitle()) && studentsBook.getAuthor().equals(b.getAuthor()) && studentsBook.getPublisher().equals(b.getPublisher())) {
                        reservedBooksString.add((reservedBooks.size() + 1) + "- نام کتاب: " + b.getTitle() + " نام نویسنده: " + b.getAuthor() + " ناشر: " + b.getPublisher());
                        reservedBooks.add(b);
                    }
            }
        }
        availableBooksList.setItems(availableBooksString);
        reservedBooksList.setItems(reservedBooksString);
    }

    public void goBackToHomepage() throws IOException {
        new BookFileStream().write(allBooks);
        new PageLoader().loadScene("/View/StudentHomepage.fxml");
    }

    public void okBook(ActionEvent actionEvent) {
        boolean changed = false;
        if(actionEvent.getSource() == reserveButton) {
            int bookIndex = Integer.valueOf(bookNumberTextField1.getText()) - 1;
            if (bookIndex >=0 && bookIndex < availableBooks.size() && reservedBooks.size() < 3) {
                Book book = availableBooks.get(bookIndex);
                for (int i = 0; i < allBooks.size(); i++) {
                    if (allBooks.get(i).getTitle().equals(book.getTitle()) && allBooks.get(i).getAuthor().equals(book.getAuthor()) && allBooks.get(i).getPublisher().equals(book.getPublisher())) {
                        book.setConfirmed(false);
                        book.setReserved(true);
                        allBooks.set(i, book);
                        List<Book> studentsBooks = signedInStudent.getRESERVED_BOOKS();
                        studentsBooks.add(book);
                        signedInStudent.setRESERVED_BOOKS(studentsBooks);
                        changed = true;
                        break;
                    }
                }
            }
        }
        else if (actionEvent.getSource() == giveBackButton) {
            int bookIndex = Integer.valueOf(bookNumberTextField2.getText()) - 1;
            if (bookIndex >=0 && bookIndex < reservedBooks.size()) {
                Book book = reservedBooks.get(bookIndex);
                for (int i = 0; i < allBooks.size(); i++) {
                    if (allBooks.get(i).getTitle().equals(book.getTitle()) && allBooks.get(i).getAuthor().equals(book.getAuthor()) && allBooks.get(i).getPublisher().equals(book.getPublisher())) {
                        book.setReserved(false);
                        allBooks.set(i, book);
                        List<Book> studentsBooks = signedInStudent.getRESERVED_BOOKS();
                        for (int j = 0; j < studentsBooks.size(); j++) {
                            Book studentsBook = studentsBooks.get(j);
                            if (studentsBook.getTitle().equals(book.getTitle()) && studentsBook.getAuthor().equals(book.getAuthor()) && studentsBook.getPublisher().equals(book.getPublisher())) {
                                studentsBooks.remove(j);
                                break;
                            }
                        }
                        signedInStudent.setRESERVED_BOOKS(studentsBooks);
                        changed = true;
                        break;
                    }
                }
            }
        }
        if (changed) {
            StudentFileStream sfs = new StudentFileStream();
            List<Student> allStudents = sfs.read();
            for (int i = 0; i < allStudents.size(); i++) {
                if (allStudents.get(i).getUsername().equals(signedInStudent.getUsername())) {
                    allStudents.set(i, signedInStudent);
                    break;
                }
            }
            sfs.write(allStudents);
            showBooks();
        }
    }
}

