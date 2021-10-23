package innopolis.university.differentialequationproject.ErrorCalculators;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.SolutionMethodsClasses.Solution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class GTECalculator {
    private final LTECalculator lteCalculator;

    public GTECalculator(Solution methodToCompare) {
        this.lteCalculator = new LTECalculator(methodToCompare);
    }

    public ObservableList<XYChart.Data<Number, Number>> getGTE(List<Number> steps, InitialValueProblem initialValueProblem, int maxN) throws IllegalArgumentException{
        if(steps.size() > maxN){
            throw new IllegalArgumentException("Max N cannot be less than N");
        }


        ObservableList<XYChart.Data<Number,Number>> errors = FXCollections.observableArrayList();
        for(int currentN = steps.size(); currentN < maxN; currentN++){
            double step = (steps.get(steps.size()-1).doubleValue() - steps.get(0).doubleValue())/currentN;

            ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
            for(double x = initialValueProblem.getX0(); x < steps.get(steps.size()-1).doubleValue(); x+=step){
                listOfNewSteps.add(x);
            }

            errors.add(new XYChart.Data<>(currentN, lteCalculator.getMaxLTE(listOfNewSteps, initialValueProblem)));
        }

        return errors;
    }
}
