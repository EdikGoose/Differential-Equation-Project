package innopolis.university.differentialequationproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainPane extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainGraphsPane mainGraphsPane = new MainGraphsPane();



        Scene scene = new Scene(mainGraphsPane.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);

        primaryStage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}