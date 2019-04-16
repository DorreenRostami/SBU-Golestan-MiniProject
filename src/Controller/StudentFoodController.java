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

    @FXML
    public Button backButton, doneButton;
    @FXML
    public CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    @FXML
    public Text theBalance, notEnoughLabel;

    public void initialize(){
        loadFood();
    }

    private void loadFood(){
        theBalance.setText(Long.toString(signedInStudent.getBalance()));
        FoodFileStream ffs = new FoodFileStream();
        List<Food> reservedFood = ffs.read("Resources/Files/reservedFoods");
        for(Food f: reservedFood){
            if (f.getDay() == Day.shanbe){
                if(checkBox1.getText().length() == 0)
                    checkBox1.setText(f.getName() + " " + f.getPrice());
                else
                    checkBox2.setText(f.getName() + " " + f.getPrice());
            }
            else if (f.getDay() == Day.yekshanbe){
                if(checkBox3.getText().length() == 0)
                    checkBox3.setText(f.getName() + " " + f.getPrice());
                else
                    checkBox4.setText(f.getName() + " " + f.getPrice());
            }
            else if (f.getDay() == Day.doshanbe){
                if(checkBox5.getText().length() == 0)
                    checkBox5.setText(f.getName() + " " + f.getPrice());
                else
                    checkBox6.setText(f.getName() + " " + f.getPrice());
            }
            else if (f.getDay() == Day.seshanbe){
                if(checkBox7.getText().length() == 0)
                    checkBox7.setText(f.getName() + " " + f.getPrice());
                else
                    checkBox8.setText(f.getName() + " " + f.getPrice());
            }
            else if (f.getDay() == Day.chaharshanbe){
                if(checkBox9.getText().length() == 0)
                    checkBox9.setText(f.getName() + " " + f.getPrice());
                else
                    checkBox10.setText(f.getName() + " " + f.getPrice());
            }
        }
        if(checkBox1.getText().equals(""))
            checkBox1.setVisible(false);
        if(checkBox2.getText().equals(""))
            checkBox2.setVisible(false);
        if(checkBox3.getText().equals(""))
            checkBox3.setVisible(false);
        if(checkBox4.getText().equals(""))
            checkBox4.setVisible(false);
        if(checkBox5.getText().equals(""))
            checkBox5.setVisible(false);
        if(checkBox6.getText().equals(""))
            checkBox6.setVisible(false);
        if(checkBox7.getText().equals(""))
            checkBox7.setVisible(false);
        if(checkBox8.getText().equals(""))
            checkBox8.setVisible(false);

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
        int total = 0;
        if(checkBox1.isSelected()){
            String str = checkBox1.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        else if(checkBox2.isSelected()){
            String str = checkBox2.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        if(checkBox3.isSelected()){
            String str = checkBox3.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        else if(checkBox4.isSelected()){
            String str = checkBox4.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        if(checkBox5.isSelected()){
            String str = checkBox5.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        else if(checkBox6.isSelected()){
            String str = checkBox6.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        if(checkBox7.isSelected()){
            String str = checkBox7.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        else if(checkBox8.isSelected()){
            String str = checkBox8.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        if(checkBox9.isSelected()){
            String str = checkBox9.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        else if(checkBox10.isSelected()){
            String str = checkBox10.getText();
            int i = str.indexOf(" ");
            str = str.substring(i + 1);
            total += Integer.valueOf(str);
        }
        if (total > signedInStudent.getBalance())
            notEnoughLabel.setVisible(true);
        else {
            signedInStudent.setBalance(signedInStudent.getBalance()- total);
            try {
                goBackToHomepage();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
