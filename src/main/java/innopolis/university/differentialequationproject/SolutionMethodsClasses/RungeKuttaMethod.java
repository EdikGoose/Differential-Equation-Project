package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class RungeKuttaMethod implements Solution{

    private double getNextY(double previousX, double previousY, double sizeOfStep){
        double K1 = sizeOfStep*func(previousX, previousY);
        double K2 = sizeOfStep*func(previousX + sizeOfStep*0.5, previousY + K1*0.5);
        double K3 = sizeOfStep*func(previousX + sizeOfStep*0.5,previousY + K2*0.5);
        double K4 = sizeOfStep*func(previousX + sizeOfStep, previousY + K3);

        return previousY + (1.0/6.0)*(K1 + 2*K2 + 2*K3 + K4);
    }
    @Override
    public ObservableList<XYChart.Data<Number, Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) {
        ObservableList<XYChart.Data<Number,Number>> values = FXCollections.observableArrayList();
        values.add(new XYChart.Data<>(initialValueProblem.getX0(), initialValueProblem.getY0()));
        double sizeOfStep = steps.get(1).doubleValue() - steps.get(0).doubleValue();


        for(int index = 1; index < steps.size(); index++){
            values.add(new XYChart.Data<Number, Number>(steps.get(index), getNextY(steps.get(index-1).doubleValue(), values.get(index-1).getYValue().doubleValue(), sizeOfStep)));
        }

        return values;
    }
}
