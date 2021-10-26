package innopolis.university.differentialequationproject.paneWrappers;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.graphsControllers.GraphsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.LinkedHashMap;


public class SettingsPaneWrapper {
    private final ScrollPane root;

    private final VBox settingsBox;
    private final Label exceptionLabel;
    private final LinkedHashMap<String, TextField> inputFields;

    public SettingsPaneWrapper(ObservableList<GraphsController> listOfCharts, TabPane paneOfCharts) {
        root = new ScrollPane();
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setFitToWidth(true);


        this.settingsBox = new VBox();
        this.settingsBox.setStyle("-fx-border-color: black;");
        this.settingsBox.setAlignment(Pos.CENTER);
        this.settingsBox.setSpacing(10);

        this.inputFields = new LinkedHashMap<>();

        this.exceptionLabel = new Label("ERROR");
        this.exceptionLabel.setStyle("-fx-border-color: red;");

        addMathFormula();
        addInputFields();
        addCheckBoxes(listOfCharts);
        addUpdateButton(listOfCharts, paneOfCharts);


        root.setContent(settingsBox);
    }

    private void addInputFields(){
        inputFields.put("X0",new TextField("1"));
        inputFields.put("Y0",new TextField("10"));
        inputFields.put("Max X",new TextField("10"));
        inputFields.put("N",new TextField("100"));
        inputFields.put("Max N",new TextField("200"));

        for(var inputField : inputFields.entrySet()){
            settingsBox.getChildren().add(new Label(inputField.getKey()));
            settingsBox.getChildren().add(inputField.getValue());
        }
    }

    private void addMathFormula(){
        WebView browser = new WebView();

        browser.setMaxHeight(300);
        WebEngine webEngine = browser.getEngine();
        String html =  """
                        <!-- LOAD THE MATHJAX LIBRARY -->
                        <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
                        <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>

                        $$\\text{Differential equation: }$$
                        $$\\begin{cases}y' = \\frac{\\sqrt{y-x}}{\\sqrt{x}} + 1\\\\y(1)=10 \\\\ x \\in (1;10)\\end{cases}\\\\ $$

                        $$\\text{Solution: }$$
                        $$\\begin{cases}y = x(\\frac{1}{C^2x} - \\frac{2}{C\\sqrt{x}}+2) \\text{, where } x > 0, C \\ne 0
                        \\\\C_1 = \\frac{1}{4} \\text{, } C_2 = -\\frac{1}{2}\\end{cases}\\\\$$""";

        webEngine.loadContent(html);

        settingsBox.getChildren().add(browser);
    }

    private void addCheckBoxes(ObservableList<GraphsController> listOfLineCharts){
        VBox checkBoxes = new VBox();
        checkBoxes.setStyle("-fx-border-color: black;");
        checkBoxes.setSpacing(10);

        ObservableList<CheckBox> listOfCheckBoxes = FXCollections.observableArrayList();
        for (String nameOfGraph : listOfLineCharts.get(1).getNamesOfGraphs()) {
            CheckBox checkBox = new CheckBox(nameOfGraph);
            checkBox.setSelected(true);

            listOfCheckBoxes.add(checkBox);
        }

        for (var checkBox : listOfCheckBoxes) {
            checkBoxes.getChildren().add(checkBox);
            checkBox.setOnAction(boxIsSelected -> {
                for (var chart : listOfLineCharts) {
                    chart.setVisibilityOfGraph(checkBox.getText(), checkBox.isSelected());
                }
            });
        }
        settingsBox.getChildren().add(checkBoxes);
    }

    private void addUpdateButton(ObservableList<GraphsController> listOfLineCharts, TabPane paneOfCharts){
        Button updateButton = new Button("Update");
        settingsBox.getChildren().add(updateButton);

        updateButton.setOnAction(buttonIsPressed -> {
            try {
                for (var lineChart : listOfLineCharts) {
                    paneOfCharts.setVisible(true);
                    lineChart.update(
                            new InitialValueProblem(Double.parseDouble(inputFields.get("X0").getText()), Double.parseDouble(inputFields.get("Y0").getText())),
                            Double.parseDouble(inputFields.get("Max X").getText()),
                            Integer.parseInt(inputFields.get("N").getText()),
                            Integer.parseInt(inputFields.get("Max N").getText())
                    );
                    settingsBox.getChildren().remove(exceptionLabel);
                }
            }
            catch (IllegalArgumentException ie) {
                exceptionLabel.setText("ERROR!");
                paneOfCharts.setVisible(false);
                exceptionLabel.setTooltip(new Tooltip(ie.getMessage()));
                if (!settingsBox.getChildren().contains(exceptionLabel))
                    settingsBox.getChildren().add(exceptionLabel);
            }
        });
    }


    public ScrollPane getRoot() {
        return root;
    }
}
