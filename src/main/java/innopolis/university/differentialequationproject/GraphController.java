package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SolutionMethodsClasses.EulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.ImprovedEulerMethod;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.RungeKuttaMethod;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphController {
    private final LineChart<Number, Number> graph;
    private int startNumberOfPoints = 15;

    private final SeriesOfPointsController seriesOfPointsForExact;
    private final SeriesOfPointsController seriesOfPointsForEuler;
    private final SeriesOfPointsController seriesOfPointsForImprovedEuler;
    private final SeriesOfPointsController seriesOfPointsForRunge;

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

        seriesOfPointsForExact = new SeriesOfPointsController(new DifferentialEquation(new ExactSolution()));
        seriesOfPointsForExact.setNameOfSeries("Exact Solution");

        seriesOfPointsForEuler = new SeriesOfPointsController(new DifferentialEquation(new EulerMethod()));
        seriesOfPointsForEuler.setNameOfSeries("Euler method");

        seriesOfPointsForImprovedEuler = new SeriesOfPointsController(new DifferentialEquation(new ImprovedEulerMethod()));
        seriesOfPointsForImprovedEuler.setNameOfSeries("Improved Euler method");

        seriesOfPointsForRunge = new SeriesOfPointsController(new DifferentialEquation(new RungeKuttaMethod()));
        seriesOfPointsForRunge.setNameOfSeries("Runge-Kutta Method");

        graph.getData().addAll(seriesOfPointsForExact.getSeriesOfPoints(), seriesOfPointsForEuler.getSeriesOfPoints(), seriesOfPointsForImprovedEuler.getSeriesOfPoints(), seriesOfPointsForRunge.getSeriesOfPoints());

    }

    public void update(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX){
        seriesOfPointsForExact.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
        seriesOfPointsForEuler.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
        seriesOfPointsForImprovedEuler.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
        seriesOfPointsForRunge.update(newInitialValueProblem, newNumberOfPoints, newMaxX);
    }

    public LineChart<Number, Number> getGraph() {
        return graph;
    }
}
