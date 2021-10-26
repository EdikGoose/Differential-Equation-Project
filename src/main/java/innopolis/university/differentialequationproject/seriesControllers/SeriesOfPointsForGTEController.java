package innopolis.university.differentialequationproject.seriesControllers;

import innopolis.university.differentialequationproject.errorCalculators.GTECalculator;
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
        double step = calculateStep(initialValueProblem.X0(),maxX,numberOfPoints);

        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = initialValueProblem.X0(); x < maxX; x+=step){
            listOfNewSteps.add(x);
        }

        super.seriesOfPoints.setData(errorCalculator.getGTE(listOfNewSteps, initialValueProblem, maxN));

    }
}
