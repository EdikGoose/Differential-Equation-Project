package innopolis.university.differentialequationproject.seriesControllers;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.solutionMethodsClasses.Solution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeriesOfPointsForMainController extends SeriesOfPointsController{
    private final Solution solution;

    public SeriesOfPointsForMainController(Solution solution, String name) {
        super(name);
        this.solution = solution;
    }

    @Override
    public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN) throws IllegalArgumentException {
        double step = calculateStep(initialValueProblem.X0(),maxX,numberOfPoints);

        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = initialValueProblem.X0(); x < maxX; x+=step){
            listOfNewSteps.add(x);
        }


        super.seriesOfPoints.setData(solution.solutionFunc(listOfNewSteps, initialValueProblem));
    }
}
