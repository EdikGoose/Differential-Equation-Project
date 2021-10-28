package innopolis.university.differentialequationproject.solutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ImprovedEulerMethod implements Solution{
    private double getNextY(double previousX, double previousY, double sizeOfStep){
        double K1 = sizeOfStep*func(previousX, previousY);
        double K2 = sizeOfStep*func(previousX+sizeOfStep, previousY+K1);
        return previousY + (1.0/2.0)*(K1 + K2);
    }

    @Override
    public ObservableList<XYChart.Data<Number, Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) {
        ObservableList<XYChart.Data<Number,Number>> values = FXCollections.observableArrayList();
        values.add(new XYChart.Data<>(initialValueProblem.X0(), initialValueProblem.Y0()));
        double sizeOfStep = steps.get(1).doubleValue() - steps.get(0).doubleValue();


        for(int index = 1; index < steps.size(); index++){
            values.add(new XYChart.Data<>(steps.get(index), getNextY(steps.get(index-1).doubleValue(), values.get(index-1).getYValue().doubleValue(), sizeOfStep)));
        }

        return values;
    }
}
