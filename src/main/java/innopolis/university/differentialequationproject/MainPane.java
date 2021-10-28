package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.graphsControllers.GTEGraphsController;
import innopolis.university.differentialequationproject.graphsControllers.GraphsController;
import innopolis.university.differentialequationproject.graphsControllers.LTEGraphsController;
import innopolis.university.differentialequationproject.graphsControllers.MainGraphsController;
import innopolis.university.differentialequationproject.paneWrappers.SettingsPaneWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainPane extends Application {
    private GridPane gridPaneInitialize(){
        GridPane gridPane = new GridPane();
        ColumnConstraints columnForCharts = new ColumnConstraints();
        columnForCharts.setPercentWidth(75);
        ColumnConstraints columnForSetting = new ColumnConstraints();
        columnForSetting.setPercentWidth(25);

        RowConstraints mainRow = new RowConstraints();
        mainRow.setPercentHeight(100);

        gridPane.getColumnConstraints().addAll(columnForCharts, columnForSetting);
        gridPane.getRowConstraints().add(mainRow);

        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = gridPaneInitialize();
        ObservableList<GraphsController> listOfLineCharts = FXCollections.observableArrayList();
        listOfLineCharts.addAll(new MainGraphsController("Main Graphs"), new LTEGraphsController("Local Truncation Error"), new GTEGraphsController("Global Truncation Error"));
        
        TabPane tabPaneOfCharts = new TabPane();
        for (var lineChart : listOfLineCharts) {
            var tab = new Tab(lineChart.getChart().getTitle());
            tab.setClosable(false);
            tab.setContent(lineChart.getChart());
            tabPaneOfCharts.getTabs().add(tab);
        }

        SettingsPaneWrapper settingsBox = new SettingsPaneWrapper(listOfLineCharts, tabPaneOfCharts);


        root.add(settingsBox.getRoot(), 1, 0);
        root.add(tabPaneOfCharts, 0, 0);


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}