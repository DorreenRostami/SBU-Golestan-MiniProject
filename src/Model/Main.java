package Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void init(){
        System.out.println("before loading");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PageLoader.initStage(primaryStage);
        new PageLoader().loadScene("/View/SignIn.fxml");
        List<Food> foodList = new ArrayList<>();
        Make mf = new Make();
        mf.makeFood("قورمه سبزی", 1700, foodList);
        mf.makeFood("شنینسل", 600, foodList);
        mf.makeFood("قیمه", 1700, foodList);
        mf.makeFood("قیمه بادمجان", 2000, foodList);
        mf.makeFood("ماکارونی", 1100, foodList);
        mf.makeFood("ناگت", 500, foodList);
        mf.makeFood("کباب کوبیده", 1400, foodList);
        mf.makeFood("جوجه کباب", 1800, foodList);
        mf.makeFood("زرشک پلو مرغ", 1600, foodList);
        mf.makeFood("عدس پلو", 700, foodList);
        mf.makeFood("ماهی تن", 900, foodList);
        mf.makeFood("ماهی قزل آلا", 1200, foodList);
        FoodFileStream ffs = new FoodFileStream();
        ffs.write(foodList, "Resources/Files/foods");
    }

    @Override
    public void stop(){
        System.out.println("after app");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
