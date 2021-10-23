package innopolis.university.differentialequationproject.SeriesControllers;

import innopolis.university.differentialequationproject.ErrorCalculators.LTECalculator;
import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class SeriesOfPointsForLTEController extends SeriesOfPointsController{
    private final LTECalculator errorCalculator;

    public SeriesOfPointsForLTEController(LTECalculator errorCalculator, String name) {
        super(name);
        this.errorCalculator = errorCalculator;
    }

    @Override
    public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN) throws IllegalArgumentException {
        double step = (maxX - initialValueProblem.getX0())/numberOfPoints;

        if(numberOfPoints <= 0){
            throw new IllegalArgumentException("N must be positive");
        }
        if(step <= 0){
            throw new IllegalArgumentException("X_MAX cannot be less than X_0");
        }


        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = initialValueProblem.getX0(); x < maxX; x+=step){
            listOfNewSteps.add(x);
        }

        super.seriesOfPoints.setData(errorCalculator.getLTEForSteps(listOfNewSteps, initialValueProblem));

    }
}
