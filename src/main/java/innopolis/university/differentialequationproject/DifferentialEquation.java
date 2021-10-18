package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SolutionMethodsClasses.Solution;
import javafx.scene.chart.XYChart;

import java.util.List;

public class DifferentialEquation {
    Double[] constraintsX;
    InitialValueProblem initialValueProblem;
    Solution solution;

    public DifferentialEquation(Solution solution, Double[] constraintsX, InitialValueProblem initialValueProblem) {
        this.solution = solution;
        this.constraintsX = constraintsX;
        this.initialValueProblem = initialValueProblem;
    }

    public List<XYChart.Data<Number,Number>> getListOfPoints(List<Double> steps){
       return solution.solutionFunc(steps, initialValueProblem);
    }
}
