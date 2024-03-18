module org.example.naloga2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens org.example.naloga2 to javafx.fxml;
    exports org.example.naloga2;
}