package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class StudentFoodController {
    private Student signedInStudent = (Student) new SignedInPerson().getPerson();
    private List<Food> reservedFood = new FoodFileStream().read("Resources/Files/reservedFoods");
    
    @FXML
    public Button backButton, doneButton;
    @FXML
    public CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    @FXML
    public Text theBalance, notEnoughLabel;
    @FXML
    public Text shanbeText, shanbeFood, yekshanbeText, yekshanbeFood, doshanbeText, doshanbeFood, seshanbeText, seshanbeFood, chaharshanbeText, chaharshanbeFood;

    public void initialize(){
        loadFood();
    }

    private void loadFood(){
        theBalance.setText(Long.toString(signedInStudent.getBalance()));
        theBalance.setVisible(true);
        if (reservedFood.get(0) == null)
            checkBox1.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox1.setText(reservedFood.get(0).getName() + " " + (reservedFood.get(0).getPrice() - 100));
            else
                checkBox1.setText(reservedFood.get(0).getName() + " " + reservedFood.get(0).getPrice());
        }
        if (reservedFood.get(1) == null)
            checkBox2.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox2.setText(reservedFood.get(1).getName() + " " + (reservedFood.get(1).getPrice() - 100));
            else
                checkBox2.setText(reservedFood.get(1).getName() + " " + reservedFood.get(1).getPrice());
        }
        if (reservedFood.get(2) == null)
            checkBox3.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox3.setText(reservedFood.get(2).getName() + " " + (reservedFood.get(2).getPrice() - 100));
            else
                checkBox3.setText(reservedFood.get(2).getName() + " " + reservedFood.get(2).getPrice());
        }
        if (reservedFood.get(3) == null)
            checkBox4.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox4.setText(reservedFood.get(3).getName() + " " + (reservedFood.get(3).getPrice() - 100));
            else
                checkBox4.setText(reservedFood.get(3).getName() + " " + reservedFood.get(3).getPrice());
        }
        if (reservedFood.get(4) == null)
            checkBox5.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox5.setText(reservedFood.get(4).getName() + " " + (reservedFood.get(4).getPrice() - 100));
            else
                checkBox5.setText(reservedFood.get(4).getName() + " " + reservedFood.get(4).getPrice());
        }
        if (reservedFood.get(5) == null)
            checkBox6.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox6.setText(reservedFood.get(5).getName() + " " + (reservedFood.get(5).getPrice() - 100));
            else
                checkBox6.setText(reservedFood.get(5).getName() + " " + reservedFood.get(5).getPrice());
        }
        if (reservedFood.get(6) == null)
            checkBox7.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox7.setText(reservedFood.get(6).getName() + " " + (reservedFood.get(6).getPrice() - 100));
            else
                checkBox7.setText(reservedFood.get(6).getName() + " " + reservedFood.get(6).getPrice());
        }
        if (reservedFood.get(7) == null)
            checkBox8.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox8.setText(reservedFood.get(7).getName() + " " + (reservedFood.get(7).getPrice() - 100));
            else
                checkBox8.setText(reservedFood.get(7).getName() + " " + reservedFood.get(7).getPrice());
        }
        if (reservedFood.get(8) == null)
            checkBox9.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox9.setText(reservedFood.get(8).getName() + " " + (reservedFood.get(8).getPrice() - 100));
            else
                checkBox9.setText(reservedFood.get(8).getName() + " " + reservedFood.get(8).getPrice());
        }
        if (reservedFood.get(9) == null)
            checkBox10.setVisible(false);
        else {
            if (signedInStudent.getGPA() > 17)
                checkBox10.setText(reservedFood.get(9).getName() + " " + (reservedFood.get(9).getPrice() - 100));
            else
                checkBox10.setText(reservedFood.get(9).getName() + " " + reservedFood.get(9).getPrice());
        }
        List<Food> studentsFoods = signedInStudent.getRESERVED_FOODS();
        for (Food f : studentsFoods) {
            if (f.getDay() == Day.shanbe) {
                checkBox1.setVisible(false);
                checkBox2.setVisible(false);
                shanbeText.setVisible(true);
                shanbeFood.setText(f.getName());
                shanbeFood.setVisible(true);
            }
            else if (f.getDay() == Day.yekshanbe) {
                checkBox3.setVisible(false);
                checkBox4.setVisible(false);
                yekshanbeText.setVisible(true);
                yekshanbeFood.setText(f.getName());
                yekshanbeFood.setVisible(true);
            }
            else if (f.getDay() == Day.doshanbe) {
                checkBox5.setVisible(false);
                checkBox6.setVisible(false);
                doshanbeText.setVisible(true);
                doshanbeFood.setText(f.getName());
                doshanbeFood.setVisible(true);
            }
            else if (f.getDay() == Day.seshanbe) {
                checkBox7.setVisible(false);
                checkBox8.setVisible(false);
                seshanbeText.setVisible(true);
                seshanbeFood.setText(f.getName());
                seshanbeFood.setVisible(true);
            }
            else if (f.getDay() == Day.chaharshanbe) {
                checkBox9.setVisible(false);
                checkBox10.setVisible(false);
                chaharshanbeText.setVisible(true);
                chaharshanbeFood.setText(f.getName());
                chaharshanbeFood.setVisible(true);
            }
        }
    }

    public void goBackToHomepage() throws IOException {
        new PageLoader().loadScene("/View/StudentHomepage.fxml");
    }

    public void chooseForShanbe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == checkBox1) {
            if (checkBox2.isSelected())
                checkBox2.setSelected(false);
        }
        else if (actionEvent.getSource() == checkBox2)
            if (checkBox1.isSelected())
                checkBox1.setSelected(false);
    }

    public void chooseForYekshanbe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == checkBox3)
            if (checkBox4.isSelected())
                checkBox4.setSelected(false);
            else if (actionEvent.getSource() == checkBox4)
                if (checkBox3.isSelected())
                    checkBox3.setSelected(false);
    }

    public void chooseForDoshanbe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == checkBox5)
            if (checkBox6.isSelected())
                checkBox6.setSelected(false);
            else if (actionEvent.getSource() == checkBox6)
                if (checkBox5.isSelected())
                    checkBox5.setSelected(false);
    }

    public void chooseForSeshanbe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == checkBox7)
            if (checkBox8.isSelected())
                checkBox8.setSelected(false);
            else if (actionEvent.getSource() == checkBox8)
                if (checkBox7.isSelected())
                    checkBox7.setSelected(false);
    }

    public void chooseForChaharshanbe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == checkBox9)
            if (checkBox10.isSelected())
                checkBox10.setSelected(false);
            else if (actionEvent.getSource() == checkBox10)
                if (checkBox9.isSelected())
                    checkBox9.setSelected(false);
    }

    public void done() {
        List<Food> studentsFood = signedInStudent.getRESERVED_FOODS();
        int total = 0;
        if(checkBox1.isSelected()){
            studentsFood.add(reservedFood.get(0));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(0).getPrice() - 100;
            else
                total += reservedFood.get(0).getPrice();
        }
        else if(checkBox2.isSelected()){
            studentsFood.add(reservedFood.get(1));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(1).getPrice() - 100;
            else
                total += reservedFood.get(1).getPrice();
        }
        if(checkBox3.isSelected()){
            studentsFood.add(reservedFood.get(2));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(2).getPrice() - 100;
            else
                total += reservedFood.get(2).getPrice();
        }
        else if(checkBox4.isSelected()){
            studentsFood.add(reservedFood.get(3));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(3).getPrice() - 100;
            else
                total += reservedFood.get(3).getPrice();
        }
        if(checkBox5.isSelected()){
            studentsFood.add(reservedFood.get(4));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(4).getPrice() - 100;
            else
                total += reservedFood.get(4).getPrice();
        }
        else if(checkBox6.isSelected()){
            studentsFood.add(reservedFood.get(5));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(5).getPrice() - 100;
            else
                total += reservedFood.get(5).getPrice();
        }
        if(checkBox7.isSelected()){
            studentsFood.add(reservedFood.get(6));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(6).getPrice() - 100;
            else
                total += reservedFood.get(6).getPrice();
        }
        else if(checkBox8.isSelected()){
            studentsFood.add(reservedFood.get(7));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(7).getPrice() - 100;
            else
                total += reservedFood.get(7).getPrice();
        }
        if(checkBox9.isSelected()){
            studentsFood.add(reservedFood.get(8));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(8).getPrice() - 100;
            else
                total += reservedFood.get(8).getPrice();
        }
        else if(checkBox10.isSelected()){
            studentsFood.add(reservedFood.get(9));
            if (signedInStudent.getGPA() > 17)
                total += reservedFood.get(9).getPrice() - 100;
            else
                total += reservedFood.get(9).getPrice();
        }
        if (total > signedInStudent.getBalance())
            notEnoughLabel.setVisible(true);
        else {
            signedInStudent.setBalance(signedInStudent.getBalance()- total);
            signedInStudent.setRESERVED_FOODS(studentsFood);
            List<Student> allStudents = new StudentFileStream().read();
            for (int i = 0; i < allStudents.size(); i++) {
                if (allStudents.get(i).getUsername().equals(signedInStudent.getUsername())){
                    allStudents.set(i, signedInStudent);
                    break;
                }
            }
            new StudentFileStream().write(allStudents);
            try {
                goBackToHomepage();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
