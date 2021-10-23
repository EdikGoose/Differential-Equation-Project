package innopolis.university.differentialequationproject.GraphsControllers;

import innopolis.university.differentialequationproject.ErrorCalculators.GTECalculator;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForGTEController;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GTEGraphsController extends GraphsController{
    public GTEGraphsController(String title) {
        super(title);
    }

    @Override
    protected ObservableList<SeriesOfPointsController> initializeSeries() {
        ObservableList<SeriesOfPointsController> list = FXCollections.observableArrayList();

        SeriesOfPointsForGTEController seriesOfPointsForEuler = new SeriesOfPointsForGTEController(new GTECalculator(new EulerMethod()), "Euler method");
        SeriesOfPointsForGTEController seriesOfPointsForImprovedEuler = new SeriesOfPointsForGTEController(new GTECalculator(new ImprovedEulerMethod()),"Improved Euler method");
        SeriesOfPointsForGTEController seriesOfPointsForRunge = new SeriesOfPointsForGTEController(new GTECalculator(new RungeKuttaMethod()),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
    }
}
