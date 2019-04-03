package Controller;

import Model.Day;
import Model.Food;
import Model.FoodFileStream;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodSelectingController {
    @FXML
    public Button backButton, doneButton;
    @FXML
    public TextField shanbeField1, shanbeField2, yekshanbeField1, yekshanbeField2,
            doshanbeField1, doshanbeField2, seshanbeField1, seshanbeField2, chaharshanbeField1, chaharshanbeField2;
    @FXML
    public Label rangeLabel;

    public void doneSelectingFood(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == doneButton) {
            FoodFileStream ffs = new FoodFileStream();
            List<Food> foodList = ffs.read("Resources/Files/foods");
            List<Food> reservedFoodList = new ArrayList<>();

            outer: for (int i = 0; i < 10; i++) {
                Day d = null;
                Integer element = null;
                String text = null;
                switch (i) {
                    case 0:
                        text = shanbeField1.getText();
                        if(text.length() == 0)
                            continue outer;
                        element = Integer.valueOf(text);
                        d = Day.shanbe;
                        break;
                    case 1:
                        text = shanbeField2.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.shanbe;
                        break;
                    case 2:
                        text = yekshanbeField1.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.yekshanbe;
                        break;
                    case 3:
                        text = yekshanbeField2.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.yekshanbe;
                        break;
                    case 4:
                        text = doshanbeField1.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.doshanbe;
                        break;
                    case 5:
                        text = doshanbeField2.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.doshanbe;
                        break;
                    case 6:
                        text = seshanbeField1.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.seshanbe;
                        break;
                    case 7:
                        text = seshanbeField2.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.seshanbe;
                        break;
                    case 8:
                        text = chaharshanbeField1.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.chaharshanbe;
                        break;
                    case 9:
                        text = chaharshanbeField2.getText();
                        if(text.length() == 0)
                            continue outer;
                        d = Day.chaharshanbe;
                        break;
                }
                element = Integer.valueOf(text);
                if (element > 12 || element < 1) {
                    rangeLabel.setVisible(true);
                    break;
                } else {
                    if (rangeLabel.isVisible())
                        rangeLabel.setVisible(false);
                    Food curr = foodList.get(element - 1);
                    curr.day = d;
                    reservedFoodList.add(curr);
                }
            }
            if(!rangeLabel.isVisible()) {
                ffs.write(reservedFoodList, "Resources/Files/reservedFoods");
                new PageLoader().loadScene("/View/AdminHomepage.fxml");
            }
        }
    }

    public void goBackToHomepage(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton)
            new PageLoader().loadScene("/View/AdminHomepage.fxml");
    }
}