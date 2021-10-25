package innopolis.university.differentialequationproject;


import innopolis.university.differentialequationproject.GraphsControllers.GTEGraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.GraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.LTEGraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.MainGraphsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class MainGraphsPane {
    private final GridPane root;


    private final ObservableList<GraphsController> listOfLineCharts;


    public MainGraphsPane() {
        root = new GridPane();
        this.listOfLineCharts = FXCollections.observableArrayList();
        listOfLineCharts.addAll(new MainGraphsController("Main Graphs"), new LTEGraphsController("Local Trancation Error"), new GTEGraphsController("Global Trancation Error"));

        Label exceptionLabel = new Label("ERROR");
        exceptionLabel.setStyle("-fx-border-color: red;");

        ColumnConstraints columnForCharts = new ColumnConstraints();
        columnForCharts.setPercentWidth(75);
        ColumnConstraints columnForSetting = new ColumnConstraints();
        columnForSetting.setPercentWidth(25);

        RowConstraints mainRow = new RowConstraints();
        mainRow.setPercentHeight(100);

        root.getColumnConstraints().addAll(columnForCharts, columnForSetting);
        root.getRowConstraints().add(mainRow);


        VBox chartsBox = new VBox();
        for (var lineChart : listOfLineCharts) {
            chartsBox.getChildren().add(lineChart.getChart());
            VBox.setVgrow(lineChart.getChart(), Priority.ALWAYS);
        }

        TabPane tabPaneOfCharts = new TabPane();
        for (var lineChart : listOfLineCharts) {
            var tab = new Tab(lineChart.getChart().getTitle());
            tab.setClosable(false);
            tab.setContent(lineChart.getChart());
            tabPaneOfCharts.getTabs().add(tab);
        }

        VBox settingsBox = new VBox();
        settingsBox.setStyle("-fx-border-color: black;");
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setSpacing(10);

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

        Label[] labels = new Label[]{new Label("X0:"), new Label("Y0:"), new Label("X_MAX:"), new Label("N:"), new Label("Max N: ")};
        TextField[] textFields = new TextField[]{new TextField("1"), new TextField("10"), new TextField("10"), new TextField("100"), new TextField("200")};


        for (int i = 0; i < labels.length; i++) {
            settingsBox.getChildren().add(labels[i]);
            settingsBox.getChildren().add(textFields[i]);
        }

        Button updateButton = new Button("Update");
        settingsBox.getChildren().add(updateButton);


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
        }

        for (var checkBox : listOfCheckBoxes) {
            checkBox.setOnAction(boxIsSelected -> {
                for (var chart : listOfLineCharts) {
                    chart.setVisibilityOfGraph(checkBox.getText(), checkBox.isSelected());
                }
            });
        }

        settingsBox.getChildren().add(checkBoxes);


        updateButton.setOnAction(buttonIsPressed -> {
            try {
                for (var lineChart : listOfLineCharts) {
                    tabPaneOfCharts.setVisible(true);
                    lineChart.update(
                            new InitialValueProblem(Double.parseDouble(textFields[0].getText()),
                                    Double.parseDouble(textFields[1].getText())),
                            Integer.parseInt(textFields[3].getText()),
                            Double.parseDouble(textFields[2].getText()),
                            Integer.parseInt(textFields[4].getText())
                    );
                    settingsBox.getChildren().remove(exceptionLabel);
                }
            } catch (IllegalArgumentException ie) {
                tabPaneOfCharts.setVisible(false);
                exceptionLabel.setTooltip(new Tooltip(ie.getMessage()));
                if (!settingsBox.getChildren().contains(exceptionLabel))
                    settingsBox.getChildren().add(exceptionLabel);
            }
        });




        root.add(settingsBox, 1, 0);
        root.add(tabPaneOfCharts, 0, 0);
    }


    public Pane getRoot() {
        return root;
    }


}
