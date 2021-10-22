package innopolis.university.differentialequationproject;

import innopolis.university.differentialequationproject.SolutionMethodsClasses.ExactSolution;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.Solution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ErrorCalculator {
    private final ExactSolution exactSolution;
    private final Solution methodToCompare;

    public ErrorCalculator(Solution methodToCompare) {
        this.methodToCompare = methodToCompare;
        this.exactSolution = new ExactSolution();
    }

    public ObservableList<XYChart.Data<Number, Number>> getLTEForSteps(List<Number> steps, InitialValueProblem initialValueProblem){
        ObservableList<XYChart.Data<Number,Number>> exactValues;
        ObservableList<XYChart.Data<Number,Number>> approxValues;
        ObservableList<XYChart.Data<Number,Number>> errors = FXCollections.observableArrayList();

        exactValues = exactSolution.solutionFunc(steps, initialValueProblem);
        approxValues = methodToCompare.solutionFunc(steps, initialValueProblem);

        for(int i = 0; i < exactValues.size(); i++){
            errors.add(new XYChart.Data<>(exactValues.get(i).getXValue(), Math.abs(exactValues.get(i).getYValue().doubleValue() - approxValues.get(i).getYValue().doubleValue())));
        }


        return errors;
    }


    private double getMaxLTE(List<Number> steps, InitialValueProblem initialValueProblem){
        ObservableList<XYChart.Data<Number,Number>> exactValues;
        ObservableList<XYChart.Data<Number,Number>> approxValues;

        double maxError = 0.0;

        exactValues = exactSolution.solutionFunc(steps, initialValueProblem);
        approxValues = methodToCompare.solutionFunc(steps, initialValueProblem);

        for(int i = 0; i < exactValues.size(); i++){
            maxError = Math.max(exactValues.get(i).getYValue().doubleValue() - approxValues.get(i).getYValue().doubleValue(), maxError);
        }


        return maxError;
    }



}
