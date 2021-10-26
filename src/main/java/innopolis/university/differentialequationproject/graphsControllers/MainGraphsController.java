package innopolis.university.differentialequationproject.graphsControllers;

import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsForMainController;
import innopolis.university.differentialequationproject.solutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.solutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainGraphsController extends GraphsController{
    public MainGraphsController(String title) {
        super(title);
    }

    @Override
    protected ObservableList<SeriesOfPointsController> initializeSeries() {
        ObservableList<SeriesOfPointsController> list = FXCollections.observableArrayList();

        SeriesOfPointsForMainController seriesOfPointsForExact = new SeriesOfPointsForMainController(new ExactSolution(), "Exact Solution");
        SeriesOfPointsForMainController seriesOfPointsForEuler = new SeriesOfPointsForMainController(new EulerMethod(),"Euler method");
        SeriesOfPointsForMainController seriesOfPointsForImprovedEuler = new SeriesOfPointsForMainController(new ImprovedEulerMethod(), "Improved Euler method");
        SeriesOfPointsForMainController seriesOfPointsForRunge = new SeriesOfPointsForMainController(new RungeKuttaMethod(),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForExact, seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
    }
}
