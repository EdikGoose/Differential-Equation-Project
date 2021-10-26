package innopolis.university.differentialequationproject.graphsControllers;

import innopolis.university.differentialequationproject.errorCalculators.LTECalculator;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsForLTEController;
import innopolis.university.differentialequationproject.solutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.RungeKuttaMethod;
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
