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
        double step = calculateStep(initialValueProblem.getX0(),maxX,numberOfPoints);

        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = initialValueProblem.getX0(); x < maxX; x+=step){
            listOfNewSteps.add(x);
        }

        super.seriesOfPoints.setData(errorCalculator.getLTEForSteps(listOfNewSteps, initialValueProblem));

    }
}
