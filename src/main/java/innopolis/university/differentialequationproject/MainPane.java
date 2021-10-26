package innopolis.university.differentialequationproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPane extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainGraphsPane mainGraphsPane = new MainGraphsPane();
        Scene scene = new Scene(mainGraphsPane.getRoot());

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}