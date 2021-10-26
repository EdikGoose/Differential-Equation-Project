package innopolis.university.differentialequationproject.solutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public interface Solution {
    default double func(double x, double y){
        return (Math.sqrt(y-x)/Math.sqrt(x)) +  1;
    }

    ObservableList<XYChart.Data<Number,Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) throws IllegalArgumentException;
}
