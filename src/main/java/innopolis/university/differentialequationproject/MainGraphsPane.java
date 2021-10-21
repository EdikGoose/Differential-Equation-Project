package innopolis.university.differentialequationproject;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
        exceptionLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                exceptionLabel.setTooltip(new Tooltip(t1));
            }
        });

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

        Button plotButton = new Button("PLOT");
        settingsBox.getChildren().add(plotButton);


        plotButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                graphController.updateGraph(
                        new InitialValueProblem(Double.parseDouble(textFields[0].getText()),
                                Double.parseDouble(textFields[1].getText())),
                        Integer.parseInt(textFields[3].getText()),
                        Double.parseDouble(textFields[2].getText()));
                settingsBox.getChildren().remove(exceptionLabel);
                }
                catch (IllegalArgumentException ie){
                    exceptionLabel.setText(ie.getMessage());
                    if(!settingsBox.getChildren().contains(exceptionLabel))
                        settingsBox.getChildren().add(exceptionLabel);
                }
            }
        });







        root.add(graphController.getGraph(),0,0);

        root.add(settingsBox, 1, 0);




    }


    public Pane getRoot() {
        return root;
    }
}
