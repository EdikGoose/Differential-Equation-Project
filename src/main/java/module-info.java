module innopolis.university.differentialequationproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.jfree.fxgraphics2d;
    requires jlatexmath;
    requires javafx.swing;
    requires javafx.web;

    opens innopolis.university.differentialequationproject to javafx.fxml;
    exports innopolis.university.differentialequationproject;
    exports innopolis.university.differentialequationproject.SolutionMethodsClasses;
    opens innopolis.university.differentialequationproject.SolutionMethodsClasses to javafx.fxml;
    exports innopolis.university.differentialequationproject.SeriesControllers;
    opens innopolis.university.differentialequationproject.SeriesControllers to javafx.fxml;
    exports innopolis.university.differentialequationproject.GraphsControllers;
    opens innopolis.university.differentialequationproject.GraphsControllers to javafx.fxml;
}