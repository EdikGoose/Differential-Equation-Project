package innopolis.university.differentialequationproject;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public abstract class GraphController {
    LineChart<Number, Number> graph;

    public GraphController() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X axis");
        xAxis.setAnimated(true);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");
        yAxis.setAnimated(true);

        var graph = new LineChart<>(xAxis, yAxis);
        graph.setAnimated(true);

        graph = new LineChart<>(xAxis, yAxis);
    }



    public LineChart<Number, Number> getGraph() {
        return graph;
    }
}
