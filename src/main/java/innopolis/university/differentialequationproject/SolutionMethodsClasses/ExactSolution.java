package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ExactSolution implements Solution{
    @Override
    public ObservableList<XYChart.Data<Number, Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) {
        if(initialValueProblem.getX0() <= 0){
            throw new IllegalArgumentException("x0 cannot be less or equal to 0");
        }
        if(initialValueProblem.getY0() < initialValueProblem.getX0()){
            throw new IllegalArgumentException("y0 cannot be less than x0");
        }
        if(steps.size() == 1 || steps.get(1).doubleValue()-steps.get(0).doubleValue() > 1) {
            throw new IllegalArgumentException("Please, set bigger N for effective use of methods");
        }

        double C = (-Math.sqrt(initialValueProblem.getX0())-Math.sqrt(initialValueProblem.getY0()-initialValueProblem.getX0())) / (initialValueProblem.getY0() - 2*initialValueProblem.getX0());
        ObservableList<XYChart.Data<Number,Number>> values = FXCollections.observableArrayList();

        for(Number x: steps){
            double xValue = x.doubleValue();
            if(xValue < 0){
                throw new IllegalArgumentException("x cannot be less or equal to 0");
            }
            values.add(new XYChart.Data<>(x,xValue*( (1/(C*C*xValue)) - 2/(C*Math.sqrt(xValue)) + 2)));
        }

        return values;

    }
}
