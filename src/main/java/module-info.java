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
    exports innopolis.university.differentialequationproject.solutionMethodsClasses;
    opens innopolis.university.differentialequationproject.solutionMethodsClasses to javafx.fxml;
    exports innopolis.university.differentialequationproject.seriesControllers;
    opens innopolis.university.differentialequationproject.seriesControllers to javafx.fxml;
    exports innopolis.university.differentialequationproject.graphsControllers;
    opens innopolis.university.differentialequationproject.graphsControllers to javafx.fxml;
    exports innopolis.university.differentialequationproject.paneWrappers;
    opens innopolis.university.differentialequationproject.paneWrappers to javafx.fxml;
}