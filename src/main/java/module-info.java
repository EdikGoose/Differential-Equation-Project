module innopolis.university.differentialequationproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens innopolis.university.differentialequationproject to javafx.fxml;
    exports innopolis.university.differentialequationproject;
}