package Controller;

import Model.Book;
import Model.BookFileStream;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminsLibraryController {
    private List<Book> allBooks = new BookFileStream().read();
    private List<Book> availableBooks = new ArrayList<>();
    private List<Book> reservedBooks = new ArrayList<>();
    private List<Book> unconfirmedBooks = new ArrayList<>();

    @FXML
    public Button backButton, confirmButton;
    @FXML
    public ListView<String> availableBooksList, reservedBooksList, unconfirmedBooksList;
    @FXML
    public TextField bookNumberTextField;

    public void initialize() {
        showBooks();
    }

    private void showBooks() {
        ObservableList<String> availableBooksString = FXCollections.observableArrayList();
        ObservableList<String> reservedBooksString = FXCollections.observableArrayList();
        ObservableList<String> unconfirmedBooksString = FXCollections.observableArrayList();
        availableBooks.clear();
        reservedBooks.clear();
        unconfirmedBooks.clear();
        for (Book b : allBooks) {
            if (!b.isReserved() && b.isConfirmed()) {
                availableBooksString.add((availableBooks.size() + 1) + "- نام کتاب: " + b.getTitle() + " نام نویسنده: " + b.getAuthor() + " ناشر: " + b.getPublisher());
                availableBooks.add(b);
            }
            else if (b.isReserved() && !b.isConfirmed()) {
                reservedBooksString.add((reservedBooks.size() + 1) + "- نام کتاب: " + b.getTitle() + " نام نویسنده: " + b.getAuthor() + " ناشر: " + b.getPublisher());
                reservedBooks.add(b);
            }
            else if (!b.isReserved() && !b.isConfirmed()) {
                unconfirmedBooksString.add((unconfirmedBooks.size() + 1) + "- نام کتاب: " + b.getTitle() + " نام نویسنده: " + b.getAuthor() + " ناشر: " + b.getPublisher());
                unconfirmedBooks.add(b);
            }
        }
        availableBooksList.setItems(availableBooksString);
        reservedBooksList.setItems(reservedBooksString);
        unconfirmedBooksList.setItems(unconfirmedBooksString);
    }

    public void confirmBook() {
        int bookIndex = Integer.valueOf(bookNumberTextField.getText()) - 1;
        if(bookIndex >= 0 && bookIndex < unconfirmedBooks.size()) {
           Book book  = unconfirmedBooks.get(bookIndex);
            for (int i = 0; i < allBooks.size(); i++) {
                if (allBooks.get(i).getTitle().equals(book.getTitle()) && allBooks.get(i).getAuthor().equals(book.getAuthor()) && allBooks.get(i).getPublisher().equals(book.getPublisher())) {
                    book.setConfirmed(true);
                    allBooks.set(i, book);
                    break;
                }
            }
            showBooks();
        }
        bookNumberTextField.setText("");
    }

    public void goBackToHomepage() throws IOException {
        new BookFileStream().write(allBooks);
        new PageLoader().loadScene("/View/AdminHomepage.fxml");
    }
}
