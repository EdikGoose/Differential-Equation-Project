package innopolis.university.differentialequationproject.SeriesControllers;

import innopolis.university.differentialequationproject.DifferentialEquation;
import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;


public abstract class SeriesOfPointsController {
    protected final XYChart.Series<Number, Number> seriesOfPoints;

    public SeriesOfPointsController(String name) {
        this.seriesOfPoints = new XYChart.Series<>();
        setNameOfSeries(name);
    }

    public void setNameOfSeries(String name){
        seriesOfPoints.setName(name);
    }

    public abstract void update(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX) throws IllegalArgumentException;



    public XYChart.Series<Number, Number> getSeriesOfPoints() {
        return seriesOfPoints;
    }
}
