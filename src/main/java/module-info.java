module innopolis.university.differentialequationproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens innopolis.university.differentialequationproject to javafx.fxml;
    exports innopolis.university.differentialequationproject;
    exports innopolis.university.differentialequationproject.SolutionMethodsClasses;
    opens innopolis.university.differentialequationproject.SolutionMethodsClasses to javafx.fxml;
}