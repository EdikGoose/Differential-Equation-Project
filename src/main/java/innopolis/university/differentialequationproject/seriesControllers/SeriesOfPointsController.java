package innopolis.university.differentialequationproject.seriesControllers;

import innopolis.university.differentialequationproject.InitialValueProblem;
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

    public abstract void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN);

    public XYChart.Series<Number, Number> getSeriesOfPoints() {
        return seriesOfPoints;
    }

    protected double calculateStep(double startX, double maxX, int numberOfPoints) throws IllegalArgumentException{
        double step = (maxX - startX)/numberOfPoints;

        if(numberOfPoints <= 0){
            throw new IllegalArgumentException("N must be positive");
        }
        if(step <= 0){
            throw new IllegalArgumentException("X_MAX cannot be less than X_0");
        }

        return step;
    }

    public void setVisibility(boolean isVisible){
        seriesOfPoints.getNode().setVisible(isVisible);
    }
}
