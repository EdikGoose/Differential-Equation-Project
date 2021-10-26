package innopolis.university.differentialequationproject;


import innopolis.university.differentialequationproject.graphsControllers.GTEGraphsController;
import innopolis.university.differentialequationproject.graphsControllers.GraphsController;
import innopolis.university.differentialequationproject.graphsControllers.LTEGraphsController;
import innopolis.university.differentialequationproject.graphsControllers.MainGraphsController;
import innopolis.university.differentialequationproject.paneWrappers.SettingsPaneWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;



public class MainGraphsPane {
    private final GridPane root;


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


    public MainGraphsPane() {
        root = gridPaneInitialize();
        ObservableList<GraphsController> listOfLineCharts = FXCollections.observableArrayList();
        listOfLineCharts.addAll(new MainGraphsController("Main Graphs"), new LTEGraphsController("Local Transaction Error"), new GTEGraphsController("Global Transaction Error"));


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
    }


    public Pane getRoot() {
        return root;
    }


}
