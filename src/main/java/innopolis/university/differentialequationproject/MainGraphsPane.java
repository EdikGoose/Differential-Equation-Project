package innopolis.university.differentialequationproject;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class MainGraphsPane {
    private final GridPane root;




    private final GraphController graphController;


    public MainGraphsPane() {
        root = new GridPane();
        this.graphController = new GraphController();
        TextField exceptionLabel = new TextField("Illegal input");
        exceptionLabel.setEditable(false);
        exceptionLabel.setStyle("-fx-border-color: red;");


        ColumnConstraints columnForGraph = new ColumnConstraints();
        ColumnConstraints columnForSettings = new ColumnConstraints();
        columnForGraph.setPercentWidth(80);
        columnForSettings.setPercentWidth(20);
        root.getColumnConstraints().addAll(columnForGraph, columnForSettings);
        RowConstraints mainRow = new RowConstraints();
        mainRow.setPercentHeight(100);
        root.getRowConstraints().add(mainRow);

        VBox settingsBox = new VBox();
        settingsBox.setStyle("-fx-border-color: black;");
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setSpacing(10);

        Label[] labels = new Label[]{new Label("X0"), new Label("Y0"), new Label("X_MAX"), new Label("N")};
        TextField[] textFields = new TextField[]{new TextField("1"), new TextField("10"), new TextField("10"), new TextField("100")};


        for(int i = 0;i < 4;i++){
            settingsBox.getChildren().add(labels[i]);
            settingsBox.getChildren().add(textFields[i]);
        }

        Button updateButton = new Button("Update");
        settingsBox.getChildren().add(updateButton);



        ChoiceBox<String> choicenGraph = new ChoiceBox<>(FXCollections.observableArrayList("Main Graphs","LTE","GTE"));
        settingsBox.getChildren().add(choicenGraph);


        choicenGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               if(choicenGraph.getValue().equals("Main Graphs")){
                   graphController.showMainGraphs();
               }
               else if(choicenGraph.getValue().equals("LTE")){
                   graphController.showLTE();
               }
            }
        });



        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    graphController.updateChart(
                            new InitialValueProblem(Double.parseDouble(textFields[0].getText()),
                                    Double.parseDouble(textFields[1].getText())),
                            Integer.parseInt(textFields[3].getText()),
                            Double.parseDouble(textFields[2].getText()));
                    settingsBox.getChildren().remove(exceptionLabel);
                    choicenGraph.setValue("Main Graphs");
                }
                catch (IllegalArgumentException ie){
                    exceptionLabel.setText("ERROR");
                    exceptionLabel.setTooltip(new Tooltip(ie.getMessage()));
                    if(!settingsBox.getChildren().contains(exceptionLabel))
                        settingsBox.getChildren().add(exceptionLabel);
                }
            }
        });





        root.add(graphController.getChart(),0,0);

        root.add(settingsBox, 1, 0);




    }


    public Pane getRoot() {
        return root;
    }
}
