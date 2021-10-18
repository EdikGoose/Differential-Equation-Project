package innopolis.university.differentialequationproject.SolutionMethodsClasses;

import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ExactSolution implements Solution{




    @Override
    public List<XYChart.Data<Number,Number>>  solutionFunc(List<Double> steps, InitialValueProblem initialValueProblem) {
        if(initialValueProblem.X0() <= 0){
            throw new IllegalArgumentException("x0 cannot be less or equal to 0");
        }
        if(initialValueProblem.Y0() < initialValueProblem.X0()){
            throw new IllegalArgumentException("y0 cannot be less than x0");
        }

        double C = (-Math.sqrt(initialValueProblem.X0())+Math.sqrt(initialValueProblem.Y0()-initialValueProblem.X0())) / (initialValueProblem.Y0() - 2*initialValueProblem.X0());
        List<XYChart.Data<Number,Number>> values = FXCollections.observableArrayList();

        for(double x: steps){
            if(x < 0){
                throw new IllegalArgumentException("x cannot be less or equal to 0");
            }
            values.add(new XYChart.Data<>(x,x*( (1/(C*C*x)) - 2/(C*Math.sqrt(x)) + 2)));
        }

        return values;

    }
}
