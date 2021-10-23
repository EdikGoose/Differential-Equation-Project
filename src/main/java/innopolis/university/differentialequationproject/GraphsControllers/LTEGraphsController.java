package innopolis.university.differentialequationproject.GraphsControllers;

import innopolis.university.differentialequationproject.ErrorCalculators.LTECalculator;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForLTEController;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LTEGraphsController extends GraphsController{
    public LTEGraphsController(String title) {
        super(title);
    }

    @Override
    protected ObservableList<SeriesOfPointsController> initializeSeries() {
        ObservableList<SeriesOfPointsController> list = FXCollections.observableArrayList();

        SeriesOfPointsForLTEController seriesOfPointsForEuler = new SeriesOfPointsForLTEController(new LTECalculator(new EulerMethod()),"Euler method");
        SeriesOfPointsForLTEController seriesOfPointsForImprovedEuler = new SeriesOfPointsForLTEController(new LTECalculator(new ImprovedEulerMethod()),"Improved Euler method");
        SeriesOfPointsForLTEController seriesOfPointsForRunge = new SeriesOfPointsForLTEController(new LTECalculator(new RungeKuttaMethod()),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
    }
}
