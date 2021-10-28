package innopolis.university.differentialequationproject.graphsControllers;

import innopolis.university.differentialequationproject.errorCalculators.GTECalculator;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.seriesControllers.SeriesOfPointsForGTEController;
import innopolis.university.differentialequationproject.solutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.solutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GTEGraphsController extends GraphsController{
    public GTEGraphsController(String title) {
        super(title);
        super.chart.getXAxis().setLabel("N axis");
        super.chart.getYAxis().setLabel("Error value");
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
