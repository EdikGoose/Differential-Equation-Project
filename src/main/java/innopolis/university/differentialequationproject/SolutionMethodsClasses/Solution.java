package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.scene.chart.XYChart;

import java.util.List;

public interface Solution {
    public List<XYChart.Data<Number,Number>>  solutionFunc(List<Double> steps, InitialValueProblem initialValueProblem) throws IllegalArgumentException;
}
