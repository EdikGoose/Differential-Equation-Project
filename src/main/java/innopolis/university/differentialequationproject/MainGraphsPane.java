package innopolis.university.differentialequationproject;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;



public class MainGraphsPane {
    private final GridPane root;

    private GraphController graphController;


    public MainGraphsPane() {
        root = new GridPane();
        this.graphController = new GraphController();

        ColumnConstraints columnForGraph = new ColumnConstraints();
        ColumnConstraints columnForSettings = new ColumnConstraints();
        columnForGraph.setPercentWidth(70);
        columnForSettings.setPercentWidth(30);
        root.getColumnConstraints().addAll(columnForGraph, columnForSettings);
        RowConstraints mainRow = new RowConstraints();
        mainRow.setPercentHeight(100);
        root.getRowConstraints().add(mainRow);

        VBox settingsBox = new VBox();
        settingsBox.setAlignment(Pos.CENTER);

        Label[] labels = new Label[]{new Label("X0"), new Label("Y0"), new Label("X_MAX"), new Label("N")};
        TextField[] textFields = new TextField[]{new TextField("1"), new TextField("10"), new TextField("10"), new TextField("100")};

        for(int i = 0;i < 4;i++){
            settingsBox.getChildren().add(labels[i]);
            settingsBox.getChildren().add(textFields[i]);
        }

        graphController = new GraphController();

        Button button = new Button("PLOT");
        settingsBox.getChildren().add(button);



        button.setOnAction(actionEvent -> graphController.update(
                        new InitialValueProblem(Double.parseDouble(textFields[0].getText()),
                        Double.parseDouble(textFields[1].getText())),
                        Integer.parseInt(textFields[3].getText()),
                        Double.parseDouble(textFields[2].getText()))
                        );



        root.add(graphController.getGraph(),0,0);

        root.add(settingsBox, 1, 0);




    }


    public Pane getRoot() {
        return root;
    }
}
