package Controller;

import Model.PageLoader;
import Model.SignedInPerson;
import Model.Student;
import Model.StudentFileStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class StudentAddMoneyController {
    private SignedInPerson signedInPerson = new SignedInPerson();
    private Student signedInStudent = (Student) signedInPerson.getPerson();

    @FXML
    public Button backButton, doneButton;
    @FXML
    public Text theBalance;
    @FXML
    public TextField cardNumber, cardPassword, sum;
    @FXML
    public Label wrongLabel;

    public void initialize(){
        theBalance.setText(Long.toString(signedInStudent.getBalance()));
        theBalance.setVisible(true);
    }

    public void changePage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton)
            new PageLoader().loadScene("/View/StudentHomepage.fxml");
        else if(actionEvent.getSource() == doneButton){
            if(cardNumber.getText().length() == 16 && cardPassword.getText().length() == 4){
                wrongLabel.setVisible(false);
                signedInStudent.setBalance(signedInStudent.getBalance() + Long.valueOf(sum.getText()));
                StudentFileStream sfs = new StudentFileStream();
                List<Student> studentList = sfs.read();
                for (int i = 0; i < studentList.size(); i++) {
                    if(studentList.get(i).getUsername().equals(signedInStudent.getUsername())) {
                        studentList.set(i, signedInStudent);
                        break;
                    }
                }
                sfs.write(studentList);
                new PageLoader().loadScene("/View/StudentHomepage.fxml");
            }
            else {
                wrongLabel.setVisible(true);
                cardNumber.setText("");
                cardPassword.setText("");
                sum.setText("");
            }
        }
    }
}
