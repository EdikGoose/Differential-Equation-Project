package innopolis.university.differentialequationproject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainPane extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainGraphsPane mainGraphsPane = new MainGraphsPane();

     ;

        Scene scene = new Scene(mainGraphsPane.getRoot());

        primaryStage.setScene(scene);

        primaryStage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}