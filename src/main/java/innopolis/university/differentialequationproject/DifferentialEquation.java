package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SolutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.Solution;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class DifferentialEquation {
    private Double[] constraintsX;
    private InitialValueProblem initialValueProblem;
    private Solution solution;

    public DifferentialEquation(Solution solution) {
        this.solution = solution;
        this.constraintsX = new Double[]{1.0,10.0};
        this.initialValueProblem = new InitialValueProblem(1,10);
    }

    public ObservableList<XYChart.Data<Number,Number>> getListOfSolutions(List<Number> steps){

        return solution.solutionFunc(steps, initialValueProblem);

    }

    public Double[] getConstraintsX() {
        return constraintsX;
    }

    public void setConstraintsX(Double[] constraintsX) {
        this.constraintsX = constraintsX;
    }

    public InitialValueProblem getInitialValueProblem() {
        return initialValueProblem;
    }

    public void setInitialValueProblem(InitialValueProblem initialValueProblem) {
        this.initialValueProblem = initialValueProblem;
    }
}
