package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForMainController;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.RungeKuttaMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class GraphController {
    private final LineChart<Number, Number> graph;
    private final int startNumberOfPoints = 15;



    private final ObservableList<SeriesOfPointsForMainController> seriesControllers;

    private ObservableList<SeriesOfPointsForMainController> initializeListOfSeriesForGraphController(){
        ObservableList<SeriesOfPointsForMainController> list = FXCollections.observableArrayList();

        SeriesOfPointsForMainController seriesOfPointsForExact = new SeriesOfPointsForMainController(new DifferentialEquation(new ExactSolution()), "Exact Solution");
        SeriesOfPointsForMainController seriesOfPointsForEuler = new SeriesOfPointsForMainController(new DifferentialEquation(new EulerMethod()),"Euler method");
        SeriesOfPointsForMainController seriesOfPointsForImprovedEuler = new SeriesOfPointsForMainController(new DifferentialEquation(new ImprovedEulerMethod()), "Improved Euler method");
        SeriesOfPointsForMainController seriesOfPointsForRunge = new SeriesOfPointsForMainController(new DifferentialEquation(new RungeKuttaMethod()),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForExact, seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
    }

    private ObservableList<SeriesOfPointsController> initializeListOfSeriesForErrorsController(){
        return null;
    }

    public GraphController() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X axis");
        xAxis.setAnimated(true);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");
        yAxis.setAnimated(true);

        graph = new LineChart<>(xAxis, yAxis);

        graph.setAnimated(true);
        graph.setCreateSymbols(false);

        seriesControllers = initializeListOfSeriesForGraphController();

       for(var seriesController : seriesControllers){
           graph.getData().add(seriesController.getSeriesOfPoints());
       }

    }

    public void updateGraph(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX){
       for(var seriesController : seriesControllers){
           seriesController.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
       }
    }

    public LineChart<Number, Number> getGraph() {
        return graph;
    }
}
