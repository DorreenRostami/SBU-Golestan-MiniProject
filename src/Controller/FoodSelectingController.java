package Controller;

import Model.Day;
import Model.Food;
import Model.FoodFileStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class FoodSelectingController {
    @FXML
    public Button backButton, doneButton;
    @FXML
    public TextField shanbeField1, shanbeField2, yekshanbeField1, yekshanbeField2,
            doshanbeField1, doshanbeField2, seshanbeField1, seshanbeField2, chaharshanbeField1, chaharshanbeField2;

    public void doneSelectingFood(ActionEvent actionEvent) {
        if(actionEvent.getSource() == doneButton){
            FoodFileStream ffs = new FoodFileStream();
            List<Food> foodList = ffs.read("Resources/Files/foods");
            List<Food> reservedFoodList = new ArrayList<>();

            for(int i = 0; i < 10; i++) {
                Day d = null;
                Integer element = null;
                switch (i){
                    case 0: element = Integer.valueOf(shanbeField1.getText()); d = Day.shanbe; break;
                    case 1: element = Integer.valueOf(shanbeField2.getText()); d = Day.shanbe; break;
                    case 2: element = Integer.valueOf(yekshanbeField1.getText()); d = Day.yekshanbe; break;
                    case 3: element = Integer.valueOf(yekshanbeField2.getText()); d = Day.yekshanbe; break;
                    case 4: element = Integer.valueOf(doshanbeField1.getText()); d = Day.doshanbe; break;
                    case 5: element = Integer.valueOf(doshanbeField2.getText()); d = Day.doshanbe; break;
                    case 6: element = Integer.valueOf(seshanbeField1.getText()); d = Day.seshanbe; break;
                    case 7: element = Integer.valueOf(seshanbeField2.getText()); d = Day.seshanbe; break;
                    case 8: element = Integer.valueOf(chaharshanbeField1.getText()); d = Day.chaharshanbe; break;
                    case 9: element = Integer.valueOf(chaharshanbeField2.getText()); d = Day.chaharshanbe; break;
                }
                if(element > 12 || element < 1){
                    System.out.println("wrong");
                    //TO DO: add labels
                }
                else if(element != null) {
                    Food curr = foodList.get(element - 1);
                    curr.day = d;
                    reservedFoodList.add(curr);
                }
            }
            ffs.write(reservedFoodList, "Resources/Files/reservedFoods");
        }
    }
}