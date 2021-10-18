package innopolis.university.differentialequationproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.*;

public class MainGraphsPane {
    private GridPane root;
    private VBox settingsBox;

    private ExactSolutionGraph exactSolutionGraph;

    public MainGraphsPane() {
        root = new GridPane();

        ColumnConstraints columnForGraph = new ColumnConstraints();
        ColumnConstraints columnForSettings = new ColumnConstraints();
        columnForGraph.setPercentWidth(70);
        columnForSettings.setPercentWidth(30);
        root.getColumnConstraints().addAll(columnForGraph, columnForSettings);
        RowConstraints mainRow = new RowConstraints();
        mainRow.setPercentHeight(100);
        root.getRowConstraints().add(mainRow);

        settingsBox = new VBox();
        settingsBox.setAlignment(Pos.CENTER);

        root.add(settingsBox, 1, 0);

        exactSolutionGraph = new ExactSolutionGraph();

    }

    public void addToSettingsBox(Node node) {
        settingsBox.getChildren().add(node);
        VBox.setMargin(node, new Insets(10,10,10,10));
    }

    public void addGraph(LineChart<Number, Number> graph) {
        root.add(graph, 0, 0);
    }

    public Pane getRoot() {
        return root;
    }
}
