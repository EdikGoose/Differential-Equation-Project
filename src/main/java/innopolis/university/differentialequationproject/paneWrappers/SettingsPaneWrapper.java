package innopolis.university.differentialequationproject.paneWrappers;

import innopolis.university.differentialequationproject.InitialValueProblem;
import innopolis.university.differentialequationproject.graphsControllers.GraphsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class SettingsPaneWrapper {
    private final ScrollPane root;

    private final VBox settingsBox;
    private final Label exceptionLabel;
    private final Label[] labels = new Label[]{new javafx.scene.control.Label("X0:"), new javafx.scene.control.Label("Y0:"), new javafx.scene.control.Label("X_MAX:"), new javafx.scene.control.Label("N:"), new javafx.scene.control.Label("Max N: ")};
    private final TextField[] textFields = new TextField[]{new javafx.scene.control.TextField("1"), new javafx.scene.control.TextField("10"), new javafx.scene.control.TextField("10"), new javafx.scene.control.TextField("100"), new javafx.scene.control.TextField("200")};


    public SettingsPaneWrapper(ObservableList<GraphsController> listOfCharts, TabPane paneOfCharts) {
        root = new ScrollPane();
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.settingsBox = new VBox();
        this.settingsBox.setStyle("-fx-border-color: black;");
        this.settingsBox.setAlignment(Pos.CENTER);
        this.settingsBox.setSpacing(10);

        this.exceptionLabel = new Label("ERROR");
        this.exceptionLabel.setStyle("-fx-border-color: red;");

        addMathFormula();
        addInputFields();
        addCheckBoxes(listOfCharts);
        addUpdateButton(listOfCharts, paneOfCharts);


        root.setContent(settingsBox);
    }

    private void addInputFields(){
        for (int i = 0; i < labels.length; i++) {
            settingsBox.getChildren().add(labels[i]);
            settingsBox.getChildren().add(textFields[i]);
        }
    }

    private void addMathFormula(){
        WebView browser = new WebView();

        browser.setMaxHeight(300);
        WebEngine webEngine = browser.getEngine();
        File file = new File("/home/edikgoose/IdeaProjects/Differential-Equation-Project/src/main/resources/innopolis/university/differentialequationproject/DifferentialEquation.html");
        URL url = null;
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        webEngine.load(url.toString());

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
                            new InitialValueProblem(Double.parseDouble(textFields[0].getText()),
                                    Double.parseDouble(textFields[1].getText())),
                            Integer.parseInt(textFields[3].getText()),
                            Double.parseDouble(textFields[2].getText()),
                            Integer.parseInt(textFields[4].getText())
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


    public VBox getRoot() {
        return settingsBox;
    }
}
