package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsController;
import innopolis.university.differentialequationproject.SeriesControllers.SeriesOfPointsForErrorsController;
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
    private final ObservableList<SeriesOfPointsForMainController> seriesForMainControllers;
    private final ObservableList<SeriesOfPointsForErrorsController> seriesForErrorsControllers;

    private ObservableList<SeriesOfPointsForMainController> initializeListOfSeriesForGraphController(){
        ObservableList<SeriesOfPointsForMainController> list = FXCollections.observableArrayList();

        SeriesOfPointsForMainController seriesOfPointsForExact = new SeriesOfPointsForMainController(new DifferentialEquation(new ExactSolution()), "Exact Solution");
        SeriesOfPointsForMainController seriesOfPointsForEuler = new SeriesOfPointsForMainController(new DifferentialEquation(new EulerMethod()),"Euler method");
        SeriesOfPointsForMainController seriesOfPointsForImprovedEuler = new SeriesOfPointsForMainController(new DifferentialEquation(new ImprovedEulerMethod()), "Improved Euler method");
        SeriesOfPointsForMainController seriesOfPointsForRunge = new SeriesOfPointsForMainController(new DifferentialEquation(new RungeKuttaMethod()),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForExact, seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
    }

    private ObservableList<SeriesOfPointsForErrorsController> initializeListOfSeriesForErrorsController(){
        ObservableList<SeriesOfPointsForErrorsController> list = FXCollections.observableArrayList();

        SeriesOfPointsForErrorsController seriesOfPointsForEuler = new SeriesOfPointsForErrorsController(new ErrorCalculator(new EulerMethod()),"Euler");
        SeriesOfPointsForErrorsController seriesOfPointsForImprovedEuler = new SeriesOfPointsForErrorsController(new ErrorCalculator(new ImprovedEulerMethod()),"Improved Euler method");
        SeriesOfPointsForErrorsController seriesOfPointsForRunge = new SeriesOfPointsForErrorsController(new ErrorCalculator(new RungeKuttaMethod()),"Runge-Kutta Method");

        list.addAll(seriesOfPointsForEuler, seriesOfPointsForImprovedEuler, seriesOfPointsForRunge);
        return list;
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

        seriesForMainControllers = initializeListOfSeriesForGraphController();
        seriesForErrorsControllers = initializeListOfSeriesForErrorsController();


        /*
       for(var seriesController : seriesForMainControllers){
           graph.getData().add(seriesController.getSeriesOfPoints());
       }

         */




    }

    public void updateChart(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX){
       for(var seriesController : seriesForMainControllers){
           seriesController.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
       }

       for(var seriesController : seriesForErrorsControllers){
           seriesController.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
       }

    }

    public void updateChart(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX, int maxN){
        for(var seriesController : seriesForMainControllers){
            seriesController.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
        }
        for(var seriesController : seriesForErrorsControllers){
            seriesController.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
        }

    }

    public void showMainGraphs(){
        graph.getData().clear();
        for(var seriesController : seriesForMainControllers){
            graph.getData().add(seriesController.getSeriesOfPoints());
        }

    }
    public void showLTE(){
        graph.getData().clear();
        for(var seriesController : seriesForErrorsControllers){
            graph.getData().add(seriesController.getSeriesOfPoints());
        }
    }

    public LineChart<Number, Number> getChart() {
        return graph;
    }
}
