package innopolis.university.differentialequationproject.SeriesControllers;

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


}
