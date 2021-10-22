package innopolis.university.differentialequationproject.SeriesControllers;

import innopolis.university.differentialequationproject.ErrorCalculator;
import innopolis.university.differentialequationproject.InitialValueProblem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class SeriesOfPointsForErrorsController extends SeriesOfPointsController{
    private final ErrorCalculator errorCalculator;
    private final XYChart.Series<Number, Number> seriesOfGTE;


    public SeriesOfPointsForErrorsController(ErrorCalculator errorCalculator, String name) {
        super(name);
        this.errorCalculator = errorCalculator;
        this.seriesOfGTE = new XYChart.Series<>();
        this.seriesOfGTE.setName(name);
    }

    @Override
    public void update(InitialValueProblem newInitialValueProblem, int newNumberOfPoints, double newMaxX) throws IllegalArgumentException {
        double step = (newMaxX - newInitialValueProblem.getX0())/newNumberOfPoints;

        if(newNumberOfPoints <= 0){
            throw new IllegalArgumentException("N must be positive");
        }
        if(step <= 0){
            throw new IllegalArgumentException("X_MAX cannot be less than X_0");
        }


        ObservableList<Number> listOfNewSteps = FXCollections.observableArrayList();
        for(double x = newInitialValueProblem.getX0(); x < newMaxX; x+=step){
            listOfNewSteps.add(x);
        }

        super.seriesOfPoints.setData(errorCalculator.getLTEForSteps(listOfNewSteps, newInitialValueProblem));

    }
}
