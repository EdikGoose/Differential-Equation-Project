package innopolis.university.differentialequationproject.GraphsControllers;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForLTEController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForMainController;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public abstract class GraphsController {
    protected final LineChart<Number, Number> chart;
    protected ObservableList<SeriesOfPointsController> seriesOfPointsController;

    public GraphsController(String title) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X axis");
        xAxis.setAnimated(true);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");
        yAxis.setAnimated(true);

        chart = new LineChart<>(xAxis, yAxis);
        this.seriesOfPointsController = initializeSeries();

        for(var series : seriesOfPointsController){
            chart.getData().add(series.getSeriesOfPoints());
        }

        chart.setAnimated(true);
        chart.setCreateSymbols(false);
        chart.setTitle(title);
    }

    public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN){
        for(var graph : seriesOfPointsController){
            graph.update(initialValueProblem,numberOfPoints,maxX,maxN);
        }
    }

    public LineChart<Number, Number> getChart() {
        return chart;
    }

    protected abstract ObservableList<SeriesOfPointsController> initializeSeries();
}
