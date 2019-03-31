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
//        List<Student> studentList = new ArrayList<>();
//        Make ms = new Make();
//        ms.makeStudent("ali", "alavi", studentList);
//        ms.makeStudent("vali", "valavi", studentList);
//        ms.makeStudent("taghi", "taghavi", studentList);
//        StudentFileStream ss = new StudentFileStream();
//        ss.write(studentList, "Resources/Files/students");
    }

    @Override
    public void stop(){
        System.out.println("after app");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
