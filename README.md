# DE Computational Assignment


![uml](https://github.com/EdikGoose/Differential-Equation-Project/blob/main/pictures/UML.png)
## Explanation of architecture:

### `MainPane` class build main view.

It uses `SettingsPaneWrapper` class for adding settings to view and connect them to controllers.

Settings consists of:

- Equation and solution formula in Latex
- Input fields for X0, Y0, Max X, N, Max N(for GTE).
- Check box for hide graphs for more comfortable comparison
- Update button

![settings](https://github.com/EdikGoose/Differential-Equation-Project/blob/main/pictures/Settings.png)

---

### `GraphsController` class

`MainPane` contains list of three `GraphsController` classes (For main graphs, LTE, and GTE) that controls interaction between GUI and model.

```java
public abstract class GraphsController{
	protected final LineChart<Number, Number> chart;
  protected ObservableList<SeriesOfPointsController> listOfSeriesOfPointsController;

	protected abstract ObservableList<SeriesOfPointsController> initializeSeries();

	public void update(InitialValueProblem initialValueProblem, double maxX, int numberOfPoints, int maxN){
	        for(var graph : listOfSeriesOfPointsController){
	            graph.update(initialValueProblem,numberOfPoints,maxX,maxN);
	        }
	}
}
```

- For updating graphs `GraphsController` has method `update(...)` that calls update method for each `SeriesOfPointsController` keeps by `GraphsController`
- List of `SeriesOfPointsController` is initialized by abstract method `initializeSeries()` . This method is implemened by three child-classes:
    - `LTEGraphsController`
    - `MainGraphsController`
    - `GTEGraphsController`

---

### ***`SeriesOfPointsController` class***

`GraphsConroller` class contains list of **`SeriesOfPointsController`** classes for exact solution and methods:

```java
public abstract class SeriesOfPointsController {
    protected final XYChart.Series<Number, Number> seriesOfPoints;

		...
    
    public abstract void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN);
}
```

Abstract method `update()` is implemented by three child-classes:

- `SeriesOfPointsForMainController`

    ```java
    public class SeriesOfPointsForMainController extends SeriesOfPointsController{
        private final Solution solution;
    
        @Override
        public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN) throws IllegalArgumentException {
           ... // using method func() of solution field
        }
    }
    ```

- `SeriesOfPointsForLTEController`

    ```java
    public class SeriesOfPointsForLTEController extends SeriesOfPointsController{
        private final LTECalculator errorCalculator;
    
        @Override
        public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN) throws IllegalArgumentException {
           ... // using method getLTE() of errorCalculator field
        }
    }
    ```

- `SeriesOfPointsForGTEController`

    ```java
    public class SeriesOfPointsForGTEController extends SeriesOfPointsController {
        private final GTECalculator errorCalculator;
    
        @Override
        public void update(InitialValueProblem initialValueProblem, int numberOfPoints, double maxX, int maxN){
    				... // using method getGTE() of errorCalculator field
        }
    }
    ```


### `Solution` interface

Solution interface contains `solutionFunc()` that accept the list of X value and Initial value of X, Y, and return the list of points

```java
public interface Solution {
    ObservableList<XYChart.Data<Number,Number>> solutionFunc(List<Number> steps, InitialValueProblem initialValueProblem) throws IllegalArgumentException;
}
```

It is implemented by four classes: `ExactSolution`, `EulerMethod`, `ImprovedEulerMethod`, `RungeKuttaMethod`.

Example of implementation of `ImprovedEulerMethod`:

```java
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
```

# Analysis of Methods

For comparison, let's use such input:

- y(1) = 100
- Max value of X is 10
- The number of points is 11


![Screenshot from 2021-10-27 11-55-35.png](https://github.com/EdikGoose/Differential-Equation-Project/blob/main/pictures/LTE.png)

*As we can see, the **Euler method** gives the largest error(more than* 3*!). **Improved Euler** method obviously is much better, but **Runge-Kutta** is a leader(less than* 0.003*)*

### For Global Truncation error we see such situation:

![Screenshot from 2021-10-27 11-52-42.png](https://github.com/EdikGoose/Differential-Equation-Project/blob/main/pictures/GTE.png)

*(Global Truncation Error = max error of Local Truncation Errors for N)*

For larger N we obtain fewer errors, which is obvious because numerical methods are more precise for the smaller step of the calculation.

## Version
* JavaFX 17
* Java 17


## How to use
* Clone this repository to your machine
* Build the project and enjoy


# Main contributor
* Eduard Zaripov, e.zaripov@innopolis.university