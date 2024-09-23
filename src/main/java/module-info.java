module es.jeremy.entregar {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.jeremy.entregar to javafx.fxml;
    exports es.jeremy.entregar;
}