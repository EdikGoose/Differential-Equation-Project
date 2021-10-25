package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class EulerMethod implements Solution{
    private double getNextY(double previousX, double previousY, double sizeOfStep){
        return previousY + (func(previousX, previousY)) * sizeOfStep;
    }

    @Override
    public ObservableList<XYChart.Data<Number, Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) throws IllegalArgumentException {
        ObservableList<XYChart.Data<Number,Number>> values = FXCollections.observableArrayList();
        values.add(new XYChart.Data<>(initialValueProblem.getX0(), initialValueProblem.getY0()));
        double sizeOfStep = steps.get(1).doubleValue() - steps.get(0).doubleValue();


        for(int index = 1; index < steps.size(); index++){
            values.add(new XYChart.Data<>(steps.get(index), getNextY(steps.get(index-1).doubleValue(), values.get(index-1).getYValue().doubleValue(), sizeOfStep)));
        }

        return values;
    }
}
