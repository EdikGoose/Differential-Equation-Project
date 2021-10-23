package innopolis.university.differentialequationproject.SeriesControllers;

import innopolis.university.differentialequationproject.ErrorCalculators.GTECalculator;
import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeriesOfPointsForGTEController extends SeriesOfPointsController {
    private final GTECalculator errorCalculator;

    public SeriesOfPointsForGTEController(GTECalculator errorCalculator, String name) {
        super(name);
        this.errorCalculator = errorCalculator;
    }

    @Override
    public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN){
        double step = calculateStep(initialValueProblem.getX0(),maxX,numberOfPoints);

        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = initialValueProblem.getX0(); x < maxX; x+=step){
            listOfNewSteps.add(x);
        }

        super.seriesOfPoints.setData(errorCalculator.getGTE(listOfNewSteps, initialValueProblem, maxN));

    }
}
