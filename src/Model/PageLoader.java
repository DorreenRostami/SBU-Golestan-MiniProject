package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 900;
    private static Stage stage;

    public static void initStage(Stage primStage){
        stage = primStage;
        stage.setTitle("سيستم جامع دانشگاهي");
        stage.setResizable(false);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image(Paths.get("Resources/Images/hat.jpg").toUri().toString()));
    }

    public void loadScene(String fxmlAddress) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
}