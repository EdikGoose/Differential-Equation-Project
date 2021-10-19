package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public interface Solution {
    public ObservableList<XYChart.Data<Number,Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) throws IllegalArgumentException;
}
