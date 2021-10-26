package innopolis.university.differentialequationproject.graphsControllers;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public abstract class GraphsController {
    protected final LineChart<Number, Number> chart;
    protected ObservableList<SeriesOfPointsController> listOfSeriesOfPointsController;

    public GraphsController(String title) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X axis");
        xAxis.setAnimated(true);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");
        yAxis.setAnimated(true);

        chart = new LineChart<>(xAxis, yAxis);
        this.listOfSeriesOfPointsController = initializeSeries();

        for(var series : listOfSeriesOfPointsController){
            chart.getData().add(series.getSeriesOfPoints());
        }

        chart.setAnimated(true);
        chart.setCreateSymbols(false);
        chart.setTitle(title);
    }

    public void update(InitialValueProblem initialValueProblem, double maxX, int numberOfPoints, int maxN){
        for(var graph : listOfSeriesOfPointsController){
            graph.update(initialValueProblem,numberOfPoints,maxX,maxN);
        }
    }

    public ObservableList<String> getNamesOfGraphs(){
        ObservableList<String> listOfNames = FXCollections.observableArrayList();

        for(var seriesController : listOfSeriesOfPointsController){
           listOfNames.add(seriesController.getSeriesOfPoints().getName());
        }

        return listOfNames;
    }

    public LineChart<Number, Number> getChart() {
        return chart;
    }

    protected abstract ObservableList<SeriesOfPointsController> initializeSeries();

    public void setVisibilityOfGraph(String nameOfGraph, boolean isVisible){
        for(var graph : listOfSeriesOfPointsController){
            if(nameOfGraph.equals(graph.getSeriesOfPoints().getName())){
               graph.setVisibility(isVisible);
               return;
            }
        }
    }
}
