package Model;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init(){
        System.out.println("before loading");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PageLoader.initStage(primaryStage);
        new PageLoader().loadScene("/View/SignIn.fxml");
    }

    @Override
    public void stop(){
        System.out.println("after app");
    }

    public static void main(String[] args) {
        launch(args);
    }
}