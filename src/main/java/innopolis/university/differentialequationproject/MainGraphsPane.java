package innopolis.university.differentialequationproject;


import innopolis.university.differentialequationproject.GraphsControllers.GTEGraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.GraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.LTEGraphsController;
import innopolis.university.differentialequationproject.GraphsControllers.MainGraphsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.LinkedList;
import java.util.List;


public class MainGraphsPane {
    private final GridPane root;


    private ObservableList<GraphsController> listOfLineCharts;


    public MainGraphsPane() {
        root = new GridPane();
        this.listOfLineCharts = FXCollections.observableArrayList();
        listOfLineCharts.addAll(new MainGraphsController("Main Graphs"), new LTEGraphsController("Local Trancation Error"), new GTEGraphsController("Global Trancation Error"));



        Label exceptionLabel = new Label("Illegal input");
        exceptionLabel.setStyle("-fx-border-color: red;");

        RowConstraints rowForSettings = new RowConstraints();
        rowForSettings.setPercentHeight(10);
        RowConstraints rowForLineCharts = new RowConstraints();
        rowForLineCharts.setPercentHeight(90);

        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);

        root.getRowConstraints().addAll(rowForSettings, rowForLineCharts);
        root.getColumnConstraints().add(mainColumn);


        HBox settingsBox = new HBox();
        settingsBox.setStyle("-fx-border-color: black;");
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setSpacing(10);

        Label[] labels = new Label[]{new Label("X0:"), new Label("Y0:"), new Label("X_MAX:"), new Label("N:"), new Label("Max N: ")};
        TextField[] textFields = new TextField[]{new TextField("1"), new TextField("10"), new TextField("10"), new TextField("100"), new TextField("200")};


        for(int i = 0;i < labels.length; i++){
            settingsBox.getChildren().add(labels[i]);
            settingsBox.getChildren().add(textFields[i]);
        }

        Button updateButton = new Button("Update");
        settingsBox.getChildren().add(updateButton);




        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    for (var lineChart : listOfLineCharts) {
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
                catch(IllegalArgumentException ie){
                        exceptionLabel.setText("ERROR");
                        exceptionLabel.setTooltip(new Tooltip(ie.getMessage()));
                        if (!settingsBox.getChildren().contains(exceptionLabel))
                            settingsBox.getChildren().add(exceptionLabel);
                }
            }
        });





        HBox chartsBox = new HBox();
        for(var lineChart : listOfLineCharts){
            chartsBox.getChildren().add(lineChart.getChart());
            HBox.setHgrow(lineChart.getChart(),Priority.ALWAYS);
            lineChart.getChart().setMaxWidth(Double.MAX_VALUE);
        }

        root.add(chartsBox,0,1);
        root.add(settingsBox, 0, 0);




    }


    public Pane getRoot() {
        return root;
    }
}
