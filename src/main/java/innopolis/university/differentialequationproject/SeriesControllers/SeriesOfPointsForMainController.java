package innopolis.university.differentialequationproject.SeriesControllers;

import innopolis.university.differentialequationproject.DifferentialEquation;
import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeriesOfPointsForMainController extends SeriesOfPointsController{
    private final DifferentialEquation differentialEquation;

    public SeriesOfPointsForMainController(DifferentialEquation differentialEquation, String name) {
        super(name);
        this.differentialEquation = differentialEquation;
    }

    @Override
    public void update(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX) throws IllegalArgumentException {
        differentialEquation.setConstraintsX(new Double[]{newInitialValueProblem.getX0(), newMaxX});
        differentialEquation.setInitialValueProblem(newInitialValueProblem);
        double step =  ((differentialEquation.getConstraintsX()[1] - differentialEquation.getConstraintsX()[0])/newNumberOfPoints);
        if(newNumberOfPoints <= 0){
            throw new IllegalArgumentException("N must be positive");
        }
        if(step <= 0){
            throw new IllegalArgumentException("X_MAX cannot be less than X_0");
        }


        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = differentialEquation.getConstraintsX()[0]; x < differentialEquation.getConstraintsX()[1]; x+=step){
            listOfNewSteps.add(x);
        }


        super.seriesOfPoints.setData(differentialEquation.getListOfSolutions(listOfNewSteps));
    }
}
