package innopolis.university.differentialequationproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;


public class SeriesOfPointsController {
    private DifferentialEquation differentialEquation;
    private XYChart.Series<Number, Number> seriesOfPoints;

    public SeriesOfPointsController(DifferentialEquation differentialEquation) {
        this.differentialEquation = differentialEquation;
        this.seriesOfPoints = new XYChart.Series<>();
    }

    public void setNameOfSeries(String name){
        seriesOfPoints.setName(name);
    }

    public void update(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX){
        differentialEquation.setConstraintsX(new Double[]{newInitialValueProblem.getX0(), newMaxX});
        differentialEquation.setInitialValueProblem(newInitialValueProblem);
        double step =  ((differentialEquation.getConstraintsX()[1] - differentialEquation.getConstraintsX()[0])/newNumberOfPoints);

        if(step < 0 || step > 1.0){
            throw new IllegalArgumentException("Step is not less than 1, please increase number of points");
        }

        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = differentialEquation.getConstraintsX()[0]; x < differentialEquation.getConstraintsX()[1]; x+=step){
            listOfNewSteps.add(x);
        }

        seriesOfPoints.setData(differentialEquation.getListOfPoints(listOfNewSteps));
    }



    public XYChart.Series<Number, Number> getSeriesOfPoints() {
        return seriesOfPoints;
    }
}
